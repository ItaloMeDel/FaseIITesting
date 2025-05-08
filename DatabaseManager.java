import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final Connection conn;

    public Connection getConn() {
        return conn;
    }

    public DatabaseManager() throws SQLException {
        String url = "jdbc:sqlite:database.db";
        conn = DriverManager.getConnection(url);
        System.out.println("Conexión a SQLite establecida.");
    }

    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
            System.out.println("Conexión a SQLite cerrada.");
        }
    }

    public int createUser(User user) throws SQLException {
        String sql = "INSERT INTO User(name, lastname, email, password_hash, phone_number, role, status) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPasswordHash());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getRole());
            pstmt.setBoolean(7, user.isStatus());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public User getUser(int id) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("phone_number"),
                        rs.getString("role"),
                        rs.getBoolean("status")
                );
            }
        }
        return null;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE User SET name = ?, lastname = ?, email = ?, password_hash = ?, " +
                "phone_number = ?, role = ?, status = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPasswordHash());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getRole());
            pstmt.setBoolean(7, user.isStatus());
            pstmt.setInt(8, user.getId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    public int createSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier(company_name, address, phone_number, email, status) " +
                "VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, supplier.getCompanyName());
            pstmt.setString(2, supplier.getAddress());
            pstmt.setString(3, supplier.getPhoneNumber());
            pstmt.setString(4, supplier.getEmail());
            pstmt.setBoolean(5, supplier.isStatus());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public Supplier getSupplier(int id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Supplier(
                        rs.getInt("id"),
                        rs.getString("company_name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getBoolean("status")
                );
            }
        }
        return null;
    }

    public boolean updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE Supplier SET company_name = ?, address = ?, phone_number = ?, " +
                "email = ?, status = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplier.getCompanyName());
            pstmt.setString(2, supplier.getAddress());
            pstmt.setString(3, supplier.getPhoneNumber());
            pstmt.setString(4, supplier.getEmail());
            pstmt.setBoolean(5, supplier.isStatus());
            pstmt.setInt(6, supplier.getId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteSupplier(int id) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    public int createProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product(name, description, unit_price, status) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getUnitPrice());
            pstmt.setBoolean(4, product.isStatus());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public Product getProduct(int id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("unit_price"),
                        rs.getBoolean("status")
                );
            }
        }
        return null;
    }

    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET name = ?, description = ?, unit_price = ?, status = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getUnitPrice());
            pstmt.setBoolean(4, product.isStatus());
            pstmt.setInt(5, product.getId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    public int createPurchaseOrder(PurchaseOrder purchaseOrder,
                                   List<PurchaseOrderDetail> details) throws SQLException {
        conn.setAutoCommit(false);
        int orderId = -1;

        try {
            String orderSql = "INSERT INTO PurchaseOrder(id_supplier, id_user, creation_date, " +
                    "delivery_date, status, observations) VALUES(?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, purchaseOrder.getSupplierId());
                pstmt.setInt(2, purchaseOrder.getUserId());
                pstmt.setDate(3, purchaseOrder.getCreationDate());
                pstmt.setDate(4, purchaseOrder.getDeliveryDate());
                pstmt.setString(5, purchaseOrder.getStatus());
                pstmt.setString(6, purchaseOrder.getObservations());

                pstmt.executeUpdate();

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    }
                }
            }

            if (orderId == -1) {
                throw new SQLException("No se pudo obtener el ID de la orden de compra");
            }

            String detailSql = "INSERT INTO PurchaseOrderDetails(id_purchase_order, id_product, " +
                    "amount, unit_price) VALUES(?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(detailSql)) {
                for (PurchaseOrderDetail detail : details) {
                    pstmt.setInt(1, orderId);
                    pstmt.setInt(2, detail.getProductId());
                    pstmt.setInt(3, detail.getAmount());
                    pstmt.setDouble(4, detail.getUnitPrice());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }

            conn.commit();
            return orderId;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public PurchaseOrder getPurchaseOrder(int id) throws SQLException {
        String sql = "SELECT * FROM PurchaseOrder WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new PurchaseOrder(
                        rs.getInt("id"),
                        rs.getInt("id_supplier"),
                        rs.getInt("id_user"),
                        rs.getDate("creation_date"),
                        rs.getDate("delivery_date"),
                        rs.getString("status"),
                        rs.getString("observations")
                );
            }
        }
        return null;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetails(int orderId) throws SQLException {
        List<PurchaseOrderDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM PurchaseOrderDetails WHERE id_purchase_order = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                details.add(new PurchaseOrderDetail(
                        rs.getInt("id"),
                        rs.getInt("id_purchase_order"),
                        rs.getInt("id_product"),
                        rs.getInt("amount"),
                        rs.getDouble("unit_price")
                ));
            }
        }
        return details;
    }

    public boolean updatePurchaseOrderStatus(int orderId, String newStatus) throws SQLException {
        String sql = "UPDATE PurchaseOrder SET status = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, orderId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public int registerMerchandiseReception(MerchandiseReception merchandiseReception) throws SQLException {
        String sql = "INSERT INTO MerchandiseReception(id_purchase_order, reception_date, observations) " +
                "VALUES(?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, merchandiseReception.getPurchaseOrderId());
            pstmt.setDate(2, merchandiseReception.getReceptionDate());
            pstmt.setString(3, merchandiseReception.getObservations());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<MerchandiseReception> getReceptionsForOrder(int orderId) throws SQLException {
        List<MerchandiseReception> receptions = new ArrayList<>();
        String sql = "SELECT * FROM MerchandiseReception WHERE id_purchase_order = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                receptions.add(new MerchandiseReception(
                        rs.getInt("id"),
                        rs.getInt("id_purchase_order"),
                        rs.getDate("reception_date"),
                        rs.getString("observations")
                ));
            }
        }
        return receptions;
    }

    public int getUserIdByEmail(String email) throws SQLException {
        String sql = "SELECT id FROM User WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    public int getSupplierIdByCompanyName(String companyName) throws SQLException {
        String sql = "SELECT id FROM Supplier WHERE company_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, companyName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    public int getProductIdByName(String productName) throws SQLException {
        String sql = "SELECT id FROM Product WHERE name = ? AND status = 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    public List<Integer> getPurchaseOrderIdsByDateAndSupplier(Date creationDate, int supplierId) throws SQLException {
        List<Integer> orderIds = new ArrayList<>();
        String sql = "SELECT id FROM PurchaseOrder WHERE creation_date = ? AND id_supplier = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, creationDate);
            pstmt.setInt(2, supplierId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                orderIds.add(rs.getInt("id"));
            }
        }
        return orderIds;
    }

    public List<Integer> getReceptionIdsByOrderAndDate(int purchaseOrderId, Date receptionDate) throws SQLException {
        List<Integer> receptionIds = new ArrayList<>();
        String sql = "SELECT id FROM MerchandiseReception WHERE id_purchase_order = ? AND reception_date = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, purchaseOrderId);
            pstmt.setDate(2, receptionDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                receptionIds.add(rs.getInt("id"));
            }
        }
        return receptionIds;
    }

    public List<Integer> getReceptionIdsByDate(Date receptionDate) throws SQLException {
        List<Integer> receptionIds = new ArrayList<>();
        String sql = "SELECT id FROM MerchandiseReception WHERE reception_date = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, receptionDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                receptionIds.add(rs.getInt("id"));
            }
        }
        return receptionIds;
    }

    public List<Integer> getPurchaseOrderIdsByDate(Date creationDate) throws SQLException {
        List<Integer> orderIds = new ArrayList<>();
        String sql = "SELECT id FROM PurchaseOrder WHERE creation_date = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, creationDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                orderIds.add(rs.getInt("id"));
            }
        }
        return orderIds;
    }

    public boolean userExists(String email) throws SQLException {
        return getUserIdByEmail(email) != -1;
    }

    public boolean supplierExists(String companyName) throws SQLException {
        return getSupplierIdByCompanyName(companyName) != -1;
    }

    public boolean productExists(String productName) throws SQLException {
        return getProductIdByName(productName) != -1;
    }

    public boolean purchaseOrdersExistForSupplierOnDate(Date creationDate, int supplierId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM PurchaseOrder WHERE creation_date = ? AND id_supplier = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, creationDate);
            pstmt.setInt(2, supplierId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public boolean receptionsExistForOrderOnDate(int purchaseOrderId, Date receptionDate) throws SQLException {
        String sql = "SELECT COUNT(*) FROM MerchandiseReception WHERE id_purchase_order = ? AND reception_date = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, purchaseOrderId);
            pstmt.setDate(2, receptionDate);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }
}

