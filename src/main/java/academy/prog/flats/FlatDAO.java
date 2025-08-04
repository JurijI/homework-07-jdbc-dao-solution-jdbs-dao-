package academy.prog.flats;

import academy.prog.case2.AbstractDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlatDAO extends AbstractDAO<Flat> {
    private final Connection conn;

    public FlatDAO(Connection conn, String table) {
        super(conn, table);
        this.conn = conn;
    }

    public List<Flat> findByPriceRange(int min, int max) {
        List<Flat> res = new ArrayList<>();
        String sql = "SELECT * FROM flats WHERE price BETWEEN " + min + " AND " + max;
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Flat f = new Flat();
                f.setId(rs.getInt("id"));
                f.setDistrict(rs.getString("district"));
                f.setAddress(rs.getString("address"));
                f.setArea(rs.getDouble("area"));
                f.setRooms(rs.getInt("rooms"));
                f.setPrice(rs.getInt("price"));
                res.add(f);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return res;
    }

    public List<Flat> findByMinArea(double minArea) {
        List<Flat> res = new ArrayList<>();
        String sql = "SELECT * FROM flats WHERE area > " + minArea;
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Flat f = new Flat();
                f.setId(rs.getInt("id"));
                f.setDistrict(rs.getString("district"));
                f.setAddress(rs.getString("address"));
                f.setArea(rs.getDouble("area"));
                f.setRooms(rs.getInt("rooms"));
                f.setPrice(rs.getInt("price"));
                res.add(f);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return res;
    }
}
