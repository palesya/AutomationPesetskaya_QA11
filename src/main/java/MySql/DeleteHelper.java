package MySql;

import lombok.extern.log4j.Log4j;

@Log4j
public class DeleteHelper extends DBConnector {

    private String from;
    private String where;

    public static DeleteHelper getDelete() {
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
        int count = executeUpdate("delete from " + this.from + " where " + this.where);
        if (count > 0) {
            log.debug("Data was deleted");
        } else {
            log.error("Data wasn't removed. Something went wrong");
        }
        return this;
    }
}
