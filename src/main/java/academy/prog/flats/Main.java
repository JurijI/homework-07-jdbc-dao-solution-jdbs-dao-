package academy.prog.flats;

import academy.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection()) {
            FlatDAO dao = new FlatDAO(conn, "flats");
            dao.createTable(Flat.class); // создаёт таблицу

            // добавим несколько квартир
            dao.add(new Flat("Центр", "ул. Шевченко, 5", 55.5, 2, 85000));
            dao.add(new Flat("Южный", "пр. Победы, 12", 72.0, 3, 105000));
            dao.add(new Flat("Север", "ул. Гагарина, 3", 38.0, 1, 60000));

            System.out.println("\n=== Квартиры по цене от 80000 до 110000 ===");
            List<Flat> byPrice = dao.findByPriceRange(80000, 110000);
            byPrice.forEach(System.out::println);

            System.out.println("\n=== Квартиры с площадью > 50 м2 ===");
            List<Flat> byArea = dao.findByMinArea(50);
            byArea.forEach(System.out::println);
        }
    }
}
