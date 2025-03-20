package com.comapny.master.service;

import com.comapny.master.entity.MasterFieldMetadata;
import com.comapny.master.entity.MasterMetadata;
import com.comapny.master.repository.MasterFieldMetadataRepository;
import com.comapny.master.repository.MasterMetadataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class MasterDataService {

    @Autowired
    private MasterMetadataRepository masterMetadataRepository;
    @Autowired
    private MasterFieldMetadataRepository masterFieldMetadataRepository;
    @Autowired
    private DataSource dataSource;

    public String createTable(String masterName) {
        MasterMetadata masterMetadata = masterMetadataRepository.findByMasterName(masterName);
        if (masterMetadata == null) {
            return "Master name not found.";
        }

        List<MasterFieldMetadata> fields = masterFieldMetadataRepository.findByMasterId(masterMetadata.getId());
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE " + masterName + " (");

        for (MasterFieldMetadata field : fields) {
            createTableSQL.append(field.getFieldColumnName()).append(" ").append(field.getDataType());
            if (field.getMaxLength() != null) {
                createTableSQL.append("(").append(field.getMaxLength()).append(")");
            }
            if (field.getIsNotNull()) {
                createTableSQL.append(" NOT NULL");
            }
            createTableSQL.append(", ");
        }

        // Remove the last comma and space
        createTableSQL.setLength(createTableSQL.length() - 2);
        createTableSQL.append(");");

        // Check if the table already exists
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            // Check if the table exists
            String checkTableSQL = "SHOW TABLES LIKE '" + masterName + "'";
            var resultSet = statement.executeQuery(checkTableSQL);
            if (resultSet.next()) {
                return "Table already exists.";
            }
            // Create the table
            statement.execute(createTableSQL.toString());
            return "Table created successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating table: " + e.getMessage();
        }
    }
    
}