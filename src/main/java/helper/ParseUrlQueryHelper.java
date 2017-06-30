package helper;

/**
 * Created by Douglas on 24/06/17.
 */
public class ParseUrlQueryHelper {

    private String order;
    private String limit;
    private String cons;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getConsSQL() {
        //title_ads,like,'corsa'
        String cons = "";
        if (this.cons.equals("null")) {
            return cons;
        }
        if (this.cons.indexOf(":") > 0 || this.cons.indexOf("@") > 0) {
            String type_separator = this.cons.indexOf(":") > 0 ? "and" : "or";
            String[] splitCons = null;
            if (this.cons.indexOf(":") > 0) {
                splitCons = this.cons.split(":");
            } else {
                splitCons = this.cons.split("@");
            }

            int i = 0;
            for (String c : splitCons) {
                String[] value = c.split(",");
                //cons += cons + value[0] + value[1] + value [2];
                cons += "";
                if (i != splitCons.length - 1) {
                    //mds
                    if (value[0].equals("fl_active")) {
                        cons += this.normalizeWhere(value[0], value[1], value[2]) + " and ";
                    } else if (value[0].equals("user_id")) {
                        cons += this.normalizeWhere(value[0], value[1], value[2]) + " and ";
                    } else {
                        cons += this.normalizeWhere(value[0], value[1], value[2]) + " " + type_separator + " ";
                    }
                } else {
                    cons += this.normalizeWhere(value[0], value[1], value[2]);
                }
                i++;
            }
        } else {
            String[] value = this.cons.split(",");
            if (value.length == 3) {
                cons += this.normalizeWhere(value[0], value[1], value[2]);
            } else {
                cons += "";
            }
        }
        if (!cons.equals("")) {
            return "where " + cons;
        }
        return cons;
    }

    public String normalizeWhere(String field, String operator, String value) {
        String cons = "";
        if (operator.equals("like")) {
            return field + " " + operator + " " + "'%" + value + "%'";
        }
        return field + " " + operator + " " + this.normalizeValue(value);
    }

    public String normalizeValue(String value) {
        try {
            double d = Double.valueOf(value);
            if (d == (int) d) {
                return value;
            }
            return value;
        } catch (Exception e) {
            return "'" + value + "'";
        }
    }

    public String getOrderSQL() {
        String order = "";
        if (this.order.equals("null")) {
            return order;
        } else {
            String[] splitOrder = this.order.split(",");
            return "order by " + splitOrder[0] + " " + splitOrder[1];
        }
    }

    public String getLimitSQL() {
        String limit = "";
        if (this.limit.equals("null")) {
            return limit;
        }
        return "limit " + this.limit;
    }

    public String getSQL() {
        return this.getConsSQL() + " " + this.getOrderSQL() + " " + this.getLimitSQL();
    }
}

