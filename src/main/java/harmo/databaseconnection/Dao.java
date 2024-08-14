package harmo.databaseconnection;

import harmo.Admin.FullOrderDetail;
import harmo.Admin.MessageReceived;
import harmo.productBean.DatabaseProduct;
import harmo.productBean.ProductBean;
import harmo.userBean.Address;
import harmo.userBean.Cart;
import harmo.userBean.Order;
import harmo.userBean.UserBean;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Dao {

    private static final Connection CONNECTION =
            DatabaseConnection.getConnection();
    public static boolean insertUser(UserBean bean){
        boolean result = false;
        try {
            assert CONNECTION != null;
            CONNECTION.setAutoCommit(false);
            try(PreparedStatement preparedStatement =
                        CONNECTION.prepareStatement(SQLStatements.INSERT_USER,
                                Statement.RETURN_GENERATED_KEYS))
            {
                preparedStatement.setString(1, bean.getName());
                preparedStatement.setString(2, bean.getEmail());
                preparedStatement.setString(3,bean.getMobileNumber());
                preparedStatement.setString(4,bean.getSecurityQuestion());
                preparedStatement.setString(5,bean.getSecurityAnswer());
                preparedStatement.setString(6,bean.getPassword());
                System.out.println(preparedStatement);
                int affected = preparedStatement.executeUpdate();

                if(affected > 0){
                    long id = 0;
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id = generatedKeys.getLong(1);
                        }
                    }
                    if(id > 0){
                        if(insertIdInAddressTable(id)) {
                            CONNECTION.commit();
                            result = true;
                        }else{
                            CONNECTION.rollback();
                        }
                    }
                    result= true;
                }else CONNECTION.rollback();

            }finally {
                CONNECTION.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static boolean insertIdInAddressTable(long id){
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.INSERT_USER_ID))
            {
                st.setLong(1, id);
                st.executeUpdate();
                System.out.println(st);
                return true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static boolean updateAddress(long user_id, String address,
                                        String city, String state, String country){
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.UPDATE_ADDRESS))
            {
                st.setString(1,address);
                st.setString(2,state);
                st.setString(3,city);
                st.setString(4,country);
                st.setLong(5, user_id);
                st.executeUpdate();
                System.out.println(st);
                return true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<String> getPasswordsOrEmailsOrPhone(String field){
        List<String> passwordsOrEmailsOrPhone = new ArrayList<>();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(SQLStatements.ALL);
                ResultSet rs = st.executeQuery()) {
                switch (field.toUpperCase()) {
                    case "PHONE" -> {
                        while (rs.next()) {
                            passwordsOrEmailsOrPhone.add(rs
                                    .getString("mobile_number"));
                        }
                    }
                    case "EMAIL" -> {
                        while (rs.next()) {
                            passwordsOrEmailsOrPhone.add(rs
                            .getString("email"));
                        }
                    }
                    case "PASSWORD" ->{
                        while (rs.next()) {
                            passwordsOrEmailsOrPhone.add(rs
                                    .getString("password"));
                        }
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return passwordsOrEmailsOrPhone;
    }

    public static boolean check(String email, String value, String field){
        boolean result = false;
        if(field.equals("login")) {
            if(getPasswordsOrEmailsOrPhone("email").contains(email)&&
               getPasswordsOrEmailsOrPhone("password").contains(value)){
                result = true;
            }
        } else if (field.equals("reset")) {
            if(getPasswordsOrEmailsOrPhone("email").contains(email)&&
               getPasswordsOrEmailsOrPhone("phone").contains(value)){
                result = true;
            }
        }
        return result;
    }

    public static boolean resetPassword(String email,String securityQuestion,
                                        String answer,String password){
        boolean result = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(SQLStatements
                    .UPDATE_PASSWORD)){
                st.setString(1,securityQuestion);
                st.setString(2,answer);
                st.setString(3,password);
                st.setString(4,email);
                result = st.executeUpdate() > 0;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static boolean insertNewProduct(ProductBean product){
        boolean result = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.INSERT_PRODUCT
            ))
            {
               st.setString(1, product.getProduct_name());
               st.setString(2,product.getSku());
               st.setString(3,product.getCategory());
               st.setDouble(4,product.getPrice());
               st.setString(5,product.getStatus());
               st.setInt(6,product.getStock_count());
               st.executeUpdate();
                System.out.println(st);
               result = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static int getProductId(String productName){
        int id = 0;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.GET_PRODUCT_ID))
            {
                st.setString(1,productName);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    id = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    public static List<DatabaseProduct> SelectAllProducts(){
        List<DatabaseProduct> products = new ArrayList<>();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(SQLStatements.ALL_PRODUCTS))
            {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    DatabaseProduct product = new DatabaseProduct();
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setSku(rs.getString("sku"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setCategory(rs.getString("category"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStatus(rs.getString("status"));
                    product.setStock_count(rs.getInt("stock_count"));
                    products.add(product);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    public static DatabaseProduct getProductById(int product_id){
        DatabaseProduct product = null;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(SQLStatements
                    .GET_PRODUCT_BY_ID))
            {
                st.setInt(1, product_id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    product = new DatabaseProduct();
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setSku(rs.getString("sku"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setCategory(rs.getString("category"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStatus(rs.getString("status"));
                    product.setStock_count(rs.getInt("stock_count"));
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return product;
    }

    public static boolean updateProduct(DatabaseProduct product){
        boolean result = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(SQLStatements
                    .UPDATE_PRODUCT))
            {
                st.setString(1, product.getProduct_name());
                st.setString(2, product.getCategory());
                st.setDouble(3, product.getPrice());
                st.setString(4, product.getStatus());
                st.setInt(5, product.getStock_count());
                st.setInt(6, product.getProduct_id());
                result = st.executeUpdate() > 0;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String generateSku(){
        String sku = null;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(SQLStatements
                    .MAX_ID))
            {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    DecimalFormat df = new DecimalFormat("000");
                    int id = rs.getInt("last_id")+1;
                    String c = df.format(id);
                    sku = "AAA"+c;
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return sku;
    }

    public static void deleteCart(int product_id){
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(SQLStatements
                    .DELETE_CART))
            {
                st.setInt(1, product_id);
                st.executeUpdate();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static List<String> getNames(){
        List<String> names = new ArrayList<>();
        try {
            assert CONNECTION != null;
            try (PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.NAMES
            )) {
                ResultSet itemNames = st.executeQuery();
                while (itemNames.next()) {
                    String itemName = itemNames.getString("product_name");
                    names.add(itemName);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return names;
    }

    public static List<DatabaseProduct> searchProduct(String input) {
        List<DatabaseProduct> products = new ArrayList<>();
        List<String> databaseItemNames = getNames();
        databaseItemNames.forEach(System.out::println);
        HashSet<String> matches = new HashSet<>();
        HashSet<String> innerMatches = new HashSet<>();
        int inputLength = input.length();

        if (!input.contains(" ")) {
            //in put without spaces.
            for (var nameDB : databaseItemNames) {
                String name = nameDB.replaceAll("[/()-.]", " ");
                if (name.length() >= inputLength && !name.contains(" ")) {
                    if (input.equalsIgnoreCase(name.substring(0, inputLength))) {
                        matches.add(nameDB);
                    }
                } else if (name.length() > inputLength && name.contains(" ")) {
                    var nameData = name.split(" ");
                    for (String nameDatum : nameData) {
                        if (nameDatum.length() >= inputLength) {
                            if (input.equalsIgnoreCase(nameDatum.substring(0, inputLength))) {
                                matches.add(nameDB);
                            }
                        }
                    }
                }else if(name.length() <= inputLength&& (!name.contains(" "))){
                    if(name.equalsIgnoreCase(input.substring(0,name.length()))){
                        matches.add(nameDB);
                    }
                }
            }
        } else {
            // input with spaces.
            var inputArray = input.split(" ");
            for (String inputDatum : inputArray) {
                for (var nameDB : databaseItemNames) {
                    String name = nameDB.replaceAll("[/()-.]", " ");
                    if (!name.contains(" ")) {
                        // name in the database without spaces.
                        if (inputDatum.equalsIgnoreCase(name)) {
                            matches.add(nameDB);
                        }
                    } else {
                        // name in the database with spaces and input too
                        var nameData = name.split(" ");
                        LinkedList<String> inputList = Arrays.stream(inputArray)
                                .map(String::toLowerCase)
                                .collect(Collectors.toCollection(LinkedList::new));
                        LinkedList<String> nameList = Arrays.stream(nameData)
                                .map(String::toLowerCase)
                                .collect(Collectors.toCollection(LinkedList::new));
                        if (new HashSet<>(nameList).containsAll(inputList)) {
                            //this is fine too
                            matches.add(nameDB);
                        } else if (new HashSet<>(inputList).containsAll(nameList)) {
                            // this is fine.
                            matches.add(nameDB);
                        } else {
                            List<String> inputs = new ArrayList<>(inputList);
                            Iterator<String> inputIterator = inputs.iterator();
                            while (inputIterator.hasNext()) {
                                String inputElement = inputIterator.next();
                                if (nameList.contains(inputElement)) {
                                    inputIterator.remove();
                                    innerMatches.add(inputElement);
                                }
                            }
                        }
                    }
                }
            }
        }

        // matches in case the user entered multiple words.
        if (!innerMatches.isEmpty()&&matches.isEmpty()) {
            for (var nameData : databaseItemNames) {
                String name = nameData.replaceAll("[/()-.]", " ");
                var nam = name.split(" ");
                List<String> nameList = new ArrayList<>();
                for (String s : nam) {
                    String lowerCase = s.toLowerCase();
                    nameList.add(lowerCase);
                }
                if (new HashSet<>(nameList).containsAll(innerMatches)) {
                    matches.add(nameData);
                }else if (new HashSet<>(innerMatches).containsAll(nameList)) {
                    matches.add(nameData);
                }
            }
        }
        //getting the actual products.
        if (!matches.isEmpty()) {
            for(var product_name: matches){
                DatabaseProduct product = getItemByName(product_name);
                products.add(product);
            }
        }else{
            System.out.println("LIST IS EMPTY");
        }
        return products;
    }

    public static DatabaseProduct getItemByName(String name){
        DatabaseProduct product = new DatabaseProduct();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.GET_PRODUCT_BY_NAME))
            {
                st.setString(1, name);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setSku(rs.getString("sku"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setCategory(rs.getString("category"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStatus(rs.getString("status"));
                    product.setStock_count(rs.getInt("stock_count"));

                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return product;
    }

    public static boolean addToCart(int user_id, int product_id, int qty){
        boolean result = false;
        try{
            assert CONNECTION != null;
            if(checkCart(user_id,product_id)>0){
                int cart_id = checkCart(user_id,product_id);
                updateQuantity(cart_id, qty);
                result= true;
            }else {
                try (PreparedStatement st = CONNECTION.prepareStatement(
                        SQLStatements.ADD_TO_CART)) {
                    st.setInt(1, product_id);
                    st.setInt(2, user_id);
                    st.setInt(3, qty);
                    st.executeUpdate();
                    result = true;
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    private static int checkCart(int user_id, int product_id){
        int cart_id = 0;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.CHECK_CART))
            {
                st.setInt(1,user_id);
                st.setInt(2,product_id);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    cart_id = rs.getInt("cart_id");
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return cart_id;
    }

    public static int getUserId (String email){
        int id = 0;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.GET_USER_ID))
            {
                st.setString(1, email);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("user_id");
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }
    private static void updateQuantity(int cart_id, int qty){
        try{
            assert  CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.UPDATE_QTY))
            {
                int initialQry = getQty(cart_id);
                st.setInt(1, initialQry+qty);
                st.setInt(2, cart_id);
                st.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static boolean updateCartQuantity(int product_id, int user_id,int qty,
            String method)
    {
        System.out.println("Update method");
        boolean result = false;
        try{
            assert CONNECTION != null;
            CONNECTION.setAutoCommit(false);
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.UPDATE_QTY_INCRDECR))
            {
                int initialQty = productQty(product_id,user_id);
                System.out.println("Initial qty: "+ initialQty);
                int newQty =0;
                switch (method.toUpperCase()){
                    case "INCR"-> newQty = initialQty+qty;
                    case "DECR"-> newQty = initialQty-qty;
                }
                if (newQty < 1) {
                    if (newQty == 0) {
                        if (removeCart(product_id, user_id)) {
                            CONNECTION.commit();
                            result = true;
                        } else {
                            CONNECTION.rollback();
                        }
                    } else {
                        CONNECTION.rollback();
                    }
                }else {
                    st.setInt(1, newQty);
                    st.setInt(2, product_id);
                    st.setInt(3, user_id);
                    System.out.println(st);
                    System.out.println("New qty: " + newQty);
                    st.executeUpdate();
                    CONNECTION.commit();
                    result = true;
                }
            }finally {
                CONNECTION.setAutoCommit(true);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static boolean removeCart(int product_id, int user_id) {
        try {
            assert CONNECTION != null;
            try (PreparedStatement st = CONNECTION.prepareStatement(SQLStatements.REMOVE_CART_ITEM)) {
                st.setInt(1, product_id);
                st.setInt(2, user_id);
                int rowsAffected = st.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static int productQty(int product_id, int user_id){
        System.out.println("Initial quantity Method");
        int qty = 0;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.GET_CART_QTY_FOR_INCRDECR))
            {
                st.setInt(1, product_id);
                st.setInt(2, user_id);
                System.out.println(st);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    qty = rs.getInt("quantity");
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return qty;
    }
    private static int getQty(int cart_id){
        int qty = 0;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.GET_CART_QTY))
            {
                st.setInt(1, cart_id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    qty = rs.getInt("quantity");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return qty;
    }

    public static List<Cart> getCarts(int user_id){
        Random random = new Random();
        List<Cart> carts = new ArrayList<>();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.GET_ONE_USER_CARTS))
            {
                st.setInt(1, user_id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Cart cart = new Cart();
                    var product = getProductById(
                            rs.getInt("product_id"));
                    //why am I getting an error using this random?
                    cart.setSerial_number(1000000L +
                            (long) (random.nextDouble() * (9999999L - 1000000L)));
                    cart.setProduct_name(product.getProduct_name());
                    cart.setProduct_price(product.getPrice());
                    cart.setQuantity(rs.getInt("quantity"));
                    cart.setAmount(product
                            .getPrice()*rs.getInt("quantity"));
                    carts.add(cart);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return carts;
    }

    public static UserBean fullDetails(int user_id)
    {
        UserBean user = new UserBean();
        Address userAddress = new Address();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.FULL_DETAILS))
            {
                st.setInt(1, user_id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String mobile_number = rs.getString("mobile_number");
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    String password = rs.getString("password");
                    String state = rs.getString("state");
                    String country = rs.getString("country");
                    String answer = rs.getString("answer");
                    String securityQuestion = rs.getString("security_question");
                    userAddress.setAddress(address);
                    userAddress.setCity(city);
                    userAddress.setState(state);
                    userAddress.setCountry(country);
                    user.setAddress(userAddress);
                    user.setMobileNumber(mobile_number);
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setSecurityAnswer(answer);
                    user.setSecurityQuestion(securityQuestion);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static List<String> modesOfPayment()
    {
        List<String> modes = new ArrayList<>();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.GET_MODES_OF_PAYMENT))
            {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    modes.add(rs.getString("payment_name"));
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return modes;
    }

    public static int getPaymentModeId(String paymentName)
    {
        int payment_id = 0;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.GET_PAYMENT_ID))
            {
                st.setString(1,paymentName);
                ResultSet rs = st.executeQuery();
                while (rs.next()){
                    payment_id = rs.getInt("payment_id");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return payment_id;
    }

    public  static int insertOrder(int user_id, int product_id, int quantity,
                                       double total, int payment_id)
    {
        LocalDate current_date = LocalDate.now();
        java.sql.Date order_date = java.sql.Date.valueOf(current_date);
        java.sql.Date delivery_date = java.sql.Date.valueOf(current_date
                .plusDays(3));

        int order_id = 0;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.INSERT_ORDER,Statement.RETURN_GENERATED_KEYS))
            {
                st.setInt(1, user_id);
                st.setString(2,"confirmation");
                st.setInt(3, product_id);
                st.setInt(4, quantity);
                st.setDouble(5, total);
                st.setDate(6, order_date);
                st.setDate(7, delivery_date);
                st.setInt(8, payment_id);
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    order_id = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return order_id;
    }

    public static String dates(int order_id){
        String dates = null;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.ORDER_DATES))
            {
                st.setInt(1, order_id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    dates = rs.getString("order_date")+
                    ", "+rs.getString("delivery_date");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return dates;
    }

    public static boolean updateOrderStatus(int user_id, String oldStatus,
                                         String newStatus)
    {
        boolean status = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.UPDATE_STATUS))
            {
                st.setString(1,newStatus);
                st.setInt(2,user_id);
                st.setString(3,oldStatus);
                st.executeUpdate();
                status = true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return status;
    }

    public static void deleteUserCart(int user_id){
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.DELETE_USER_CART))
            {
                st.setInt(1,user_id);
                st.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static List<Order> getMyOrders(int user_id){
        List<Order> orders = new ArrayList<>();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.GET_ORDER))
            {
                st.setInt(1,user_id);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    Order order = new Order();
                    order.setProduct_name(rs.getString("product_name"));
                    order.setPrice(rs.getDouble("price"));
                    order.setQuantity(rs.getInt("quantity"));
                    order.setAmount(rs.getDouble("total"));
                    order.setOrder_date(rs.getString("order_date"));
                    order.setDelivery_date(rs.getString("delivery_date"));
                    order.setPayment_method(rs.getString("payment_name"));
                    order.setStatus(rs.getString("status"));
                    orders.add(order);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public static boolean changePassword(int user_id, String newPassword){
        boolean status = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.CHANGE_PASSWORD))
            {
                st.setString(1,newPassword);
                st.setInt(2,user_id);
                st.executeUpdate();
                status = true;
            }
        }catch (SQLException e){
            System.out.println("Change Password:" + e.getMessage());
        }
        return status;
    }

    public static boolean updateSecurityQuestion(int user_id,
                                                 String question,String answer)
    {
        boolean status = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st= CONNECTION.prepareStatement(
                    SQLStatements.UPDATE_SECURITY_QUESTION))
            {
                st.setString(1,question);
                st.setString(2,answer);
                st.setInt(3,user_id);
                st.executeUpdate();
                status = true;
            }
        }catch (SQLException e){
            System.out.println("Update Security Question:" + e.getMessage());
        }
        return status;
    }

    public static boolean updateMobileNumber(int user_id,String mobileNumber)
    {
        boolean status = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.UPDATE_NUMBER))
            {
                st.setString(1,mobileNumber);
                st.setInt(2,user_id);
                st.executeUpdate();
                status = true;
            }
        }catch (SQLException e){
            System.out.println("Update Number:" + e.getMessage());
        }
        return status;
    }

    public  static boolean addMessage(int user_id, String subject,
                                      String message){
        boolean status = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.ADD_MESSAGE))
            {
                st.setInt(1,user_id);
                st.setString(2,subject);
                st.setString(3,message);
                st.executeUpdate();
                status = true;
            }
        }catch (SQLException e){
            System.out.println("Add Message:" + e.getMessage());
        }
        return status;
    }
    public static List<MessageReceived> getMessages(){
        List<MessageReceived> messages = new ArrayList<>();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.GET_MESSAGE))
            {
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    MessageReceived message = new MessageReceived();
                    message.setMessage_id(rs.getInt("message_id"));
                    message.setUser_id(rs.getInt("user_id"));
                    message.setSubject(rs.getString("subject"));
                    message.setMessage(rs.getString("message"));
                    message.setEmail(Dao.fullDetails(rs.getInt("user_id"))
                            .getEmail());
                    messages.add(message);
                }
            }
        }catch (SQLException e){
            System.out.println("Get messages : "+e.getMessage());
        }
        return messages;
    }

    public static List<FullOrderDetail> getOrders(String status){
        List<FullOrderDetail> orders = new ArrayList<>();
        try{
            assert CONNECTION != null;
            try(PreparedStatement st =CONNECTION.prepareStatement(
                    SQLStatements.GET_ORDERS
            )){
                st.setString(1,status);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    FullOrderDetail order = new FullOrderDetail();
                    order.setUserId(rs.getInt("user_id"));
                    order.setMobileNumber(rs.getString("mobile_number"));
                    order.setOrderDate(rs.getString("order_date"));
                    order.setDeliveryDate(rs.getString("delivery_date"));
                    order.setPaymentMethod(rs.getString("payment_name"));
                    order.setStatus(rs.getString("status"));
                    order.setTotal(rs.getDouble("total"));
                    order.setQuantity(rs.getInt("quantity"));
                    order.setProductName(rs.getString("product_name"));
                    order.setProductId(rs.getInt("product_id"));
                    order.setAddress(fullDetails(rs.getInt("user_id"))
                            .getAddress().getAddress());
                    order.setCity(fullDetails(rs.getInt("user_id"))
                            .getAddress().getCity());
                    order.setCountry(fullDetails(rs.getInt("user_id"))
                            .getAddress().getCountry());
                    order.setState(fullDetails(rs.getInt("user_id"))
                            .getAddress().getState());
                    orders.add(order);
                }
            }
        }catch (SQLException e){
            System.out.println("Get Orders: " + e.getMessage());
        }
        return orders;
    }

    public static boolean updateOrder(String status , int product_id, int user_id){
        boolean result = false;
        try{
            assert CONNECTION != null;
            try(PreparedStatement st = CONNECTION.prepareStatement(
                    SQLStatements.UPDATE_ORDER))
            {
                st.setString(1,status);
                st.setInt(2,product_id);
                st.setInt(3,user_id);
                st.executeUpdate();
                result = true;
            }
        }catch (SQLException e){
            System.out.println("Update Order: " + e.getMessage());
        }
        return result;
    }
}
