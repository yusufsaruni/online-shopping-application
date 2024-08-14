package harmo.databaseconnection;

public class SQLStatements {

    public static final String INSERT_USER = """
            INSERT INTO users
            (name, email, mobile_number, security_question, answer, password)
            VALUES (?, ?, ?, ?, ?, ?)""";
    public static final String UPDATE_ADDRESS = """
            UPDATE address SET
            address=?, state=?, city=?, country=?
            WHERE user_id=?""";
    public static final String INSERT_USER_ID = """
            INSERT INTO address
            (user_id)
            VALUES(?)""";
    public static final String UPDATE_PASSWORD= """
            UPDATE users SET
            security_question=?, answer=?, password=?
            WHERE email=?""";

    public static final String ALL = """
            SELECT * FROM users""";
    public static final String ALL_PRODUCTS = """
            SELECT * FROM product""";
    public static final String NAMES = """
            SELECT product_name
            FROM product""";
    public static final String GET_PRODUCT_BY_NAME = """
            SELECT * FROM product
            WHERE product_name=?""";
    public static final String GET_PRODUCT_BY_ID = """
            SELECT * FROM product
            WHERE product_id=?""";

    public static final String INSERT_PRODUCT = """
            INSERT INTO product
            (product_name, sku, category, price, status, stock_count)
            VALUES(?, ?, ?, ?, ?, ?)""";

    public static final String UPDATE_PRODUCT = """
            UPDATE product
            SET product_name=?, category=?, price=?, status=?, stock_count=?
            WHERE product_id=?
            """;

    public static final String MAX_ID= """
            SELECT MAX(product_id) AS last_id
            FROM product""";

    public static final String DELETE_CART= """
            DELETE FROM cart
            WHERE product_id=?
            """;
    public static final String ADD_TO_CART= """
            INSERT INTO cart(
            product_id, user_id, quantity)
            VALUES(?, ?, ?)""";
    public static final String GET_USER_ID= """
            SELECT user_id
            FROM users
            WHERE email=?""";
    public static final String CHECK_CART= """
            SELECT cart_id FROM cart
            WHERE user_id=? AND product_id=?""";
    public static final String UPDATE_QTY= """
            UPDATE cart SET quantity=?
            WHERE cart_id=?""";
    public static final String UPDATE_QTY_INCRDECR= """
            UPDATE cart SET quantity=?
            WHERE product_id=? AND user_id=?""";
    public static  final String GET_PRODUCT_ID= """
            SELECT product_id
            FROM product
            WHERE product_name=?""";

    public static final String GET_CART_QTY= """
            SELECT quantity FROM cart
            WHERE cart_id=?""";
    public static final String GET_CART_QTY_FOR_INCRDECR= """
            SELECT quantity FROM cart
            WHERE product_id=? AND user_id=?""";
    public static final String REMOVE_CART_ITEM= """
            DELETE FROM cart
            WHERE product_id=? AND user_id=?""";

    public static final String GET_ONE_USER_CARTS = """
            SELECT * FROM cart
            WHERE user_id=?""";
    public static final String FULL_DETAILS= """
            SELECT
            name, email,security_question, password, mobile_number, address,
            city, state, country, answer
            FROM users
            INNER JOIN address
            ON users.user_id=address.user_id
            WHERE users.user_id=?""";
    public static final String GET_MODES_OF_PAYMENT= """
            SELECT payment_name
            FROM payments""";
    public static final String GET_PAYMENT_ID= """
            SELECT payment_id
            FROM payments
            WHERE payment_name=?""";
    public static final String INSERT_ORDER= """
            INSERT INTO client_orders(
            user_id, status, product_id, quantity, total, order_date, delivery_date,
            payment_id)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?)""";
    public static final String ORDER_DATES= """
            SELECT order_date, delivery_date FROM client_orders
            WHERE order_id=?""";
    public static final String UPDATE_STATUS= """
            UPDATE client_orders SET
            status=?
            WHERE user_id=? AND status=?""";

    public static final String DELETE_USER_CART= """
            DELETE FROM cart
            WHERE user_id=?""";
    public static final String GET_ORDER= """
            SELECT
            p.product_name, p.price, o.quantity, o.total, o.order_date, o.delivery_date,
            pm.payment_name, o.status
            FROM client_orders o
            INNER JOIN product p
            ON o.product_id=p.product_id
            INNER JOIN payments pm
            ON o.payment_id=pm.payment_id
            WHERE o.user_id=?""";
    public static final String GET_ORDERS= """
            SELECT
            u.user_id,u.mobile_number,p.product_id,
            p.product_name, p.price, o.quantity, o.total,
            o.order_date, o.delivery_date,
            pm.payment_name, o.status
            FROM users u
            INNER JOIN client_orders o
            ON o.user_id=u.user_id
            INNER JOIN product p
            ON o.product_id=p.product_id
            INNER JOIN payments pm
            ON o.payment_id=pm.payment_id
            WHERE o.status=?""";
    public static final String CHANGE_PASSWORD= """
            UPDATE users SET password=?
            WHERE user_id=?""";

    public static final String UPDATE_SECURITY_QUESTION= """
            UPDATE users SET security_question=?, answer=?
            WHERE user_id=?""";
    public static  final String UPDATE_NUMBER = """
            UPDATE users SET mobile_number=?
            WHERE user_id=?""";
    public static final String ADD_MESSAGE= """
            INSERT INTO messages(
            user_id, subject, message)
            VALUES(?, ?, ?)""";

    public static final String GET_MESSAGE= """
            SELECT message_id, user_id, subject, message
            FROM messages""";

    public static final String UPDATE_ORDER= """
            UPDATE client_orders SET status=?
            WHERE product_id=? AND user_id=?""";
}
