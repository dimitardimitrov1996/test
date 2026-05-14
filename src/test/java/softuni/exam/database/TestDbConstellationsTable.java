package softuni.exam.database;
//TestDbConstellationsTable

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TestDbConstellationsTable {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Test
    void testConstellationsTable() throws SQLException {
        DatabaseMetaData metaData = getDatabaseMetaData();

        ResultSet columns = metaData.getColumns(null, "PUBLIC", "CONSTELLATIONS", null);

        List<String> actualColumns = new ArrayList<>();

        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            String typeName = columns.getString("TYPE_NAME");
            String nullable = columns.getString("NULLABLE");
            String columnSize = columns.getString("COLUMN_SIZE");

            actualColumns.add(String.format("%s,%s,%s,%s",
                    columnName,
                    typeName,
                    nullable,
                    columnSize
            ));
        }

        Assertions.assertEquals(3, actualColumns.size());

        Assertions.assertTrue(
                actualColumns.contains("ID,BIGINT,0,19")
                        || actualColumns.contains("ID,BIGINT,0,64"),
                "ID column metadata is different: " + actualColumns
        );

        Assertions.assertTrue(
                actualColumns.contains("DESCRIPTION,VARCHAR,0,255")
                        || actualColumns.contains("DESCRIPTION,CHARACTER VARYING,0,255"),
                "DESCRIPTION column metadata is different: " + actualColumns
        );

        Assertions.assertTrue(
                actualColumns.contains("NAME,VARCHAR,0,255")
                        || actualColumns.contains("NAME,CHARACTER VARYING,0,255"),
                "NAME column metadata is different: " + actualColumns
        );
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        DataSource dataSource = getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection.getMetaData();
    }
}