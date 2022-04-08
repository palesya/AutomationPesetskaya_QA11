package MySql;

import lombok.extern.log4j.Log4j;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class SelectHelper extends DBConnector {

    private String select;
    private String from;
    private String where;
    private ResultSet resultSet;

    public SelectHelper select(String select) {
        this.select = select;
        return this;
    }

    public SelectHelper from(String from) {
        this.from = from;
        return this;
    }

    public SelectHelper where(String where) {
        this.where = where;
        return this;
    }

    public static SelectHelper getSelect() {
        return new SelectHelper();
    }

    public SelectHelper execute() {
        String setWhere = this.where == null ? "" : " where " + this.where;
        resultSet = executeQuery("select " + this.select + " from " + this.from + setWhere);
        if (resultSet!=null){
            log.debug("Data was selected.");
        }
        else {
            log.error("Data wasn't selected.");
        }
    return this;
    }

    public List<List<String>> getListData() throws Exception {
        ResultSet resultSet = this.resultSet;
        List<List<String>> data = new ArrayList<>();
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            for (int index = 1; index <= resultSet.getMetaData().getColumnCount(); index++) {
                row.add(resultSet.getString(index));
            }
            data.add(row);
        }
        return data;
    }
}
