package MySql;

import lombok.extern.log4j.Log4j;

@Log4j
public class DeleteHelper extends DBConnector{

    private String from;
    private String where;

    public static DeleteHelper getUpdate() {
        return new DeleteHelper();
    }

    public DeleteHelper from(String from) {
        this.from = from;
        return this;
    }

    public DeleteHelper where(String where) {
        this.where = where;
        return this;
    }

    public DeleteHelper execute() {
        executeQuery("delete from" + this.from + " where " + this.where);
        log.debug("Data was deleted");
        return this;
    }
}
