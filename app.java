import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    Infrastructure infrastructure = new Infrastructure();

    System.out.println(infrastructure.getAllInfo(1));
    System.out.println(infrastructure.getAllInfo(2));
    System.out.println(infrastructure.getAllInfo(3));
    System.out.println(infrastructure.getAllInfo(4));
  }
}

class Infrastructure {
  public Infrastructure() {
    init();
  }

  Db db;

  public Db getDb() {
    return db;
  }

  public String getAllInfo(int idCinema) {
    Cinema c = db.films.get(idCinema - 1);

    return String.format("%d %s %s %s",
        c.id,
        c.name,
        db.genres.get(c.genre - 1).name,
        db.prod.get(c.filmProd - 1).titleName);
  }

  Db init() {
    db = new Db();
    Cinema c1 = new Cinema(1, "РўСЊРјР°", 1, 1);
    Cinema c2 = new Cinema(2, "РЎРІРµС‚", 1, 2);
    Cinema c3 = new Cinema(3, "РћСЃРѕР±РµРЅРЅРѕСЃС‚Рё РѕС…РѕС‚С‹...", 3, 4);
    Cinema c4 = new Cinema(4, "Р§РµР»РѕРІРµРє РїР°СѓРє", 3, 3);

    db.films.add(c1);
    db.films.add(c2);
    db.films.add(c3);
    db.films.add(c4);

    db.genres.add(new Genre(1, "РЈР¶Р°СЃС‹"));
    db.genres.add(new Genre(2, "Р”СЂР°РјР°"));
    db.genres.add(new Genre(3, "РљРѕРјРµРґРёСЏ"));
    FilmProducerFactory pf = new FilmProducerFactory();
    db.addFilmProducer(pf.getFilmProducer("Р›РµРЅС„РёР»СЊРј"));
    db.addFilmProducer(pf.getFilmProducer("РњР°СЂРІРµР»"));
    db.addFilmProducer(pf.getFilmProducer("РњРѕСЃС„РёР»СЊРј"));
    db.addFilmProducer(pf.getFilmProducer("DC"));

    return db;
  }
}

class Db {
  ArrayList<Cinema> films = new ArrayList<>();
  ArrayList<FilmProducer> prod = new ArrayList<>();
  ArrayList<Genre> genres = new ArrayList<>();

  public void addFilmProducer(FilmProducer producer) {
    prod.add(producer);
  }
}

class Cinema {
  int id;
  int filmProd;
  String name;
  int genre;

  public Cinema(int id, String name, int genre, int filmProd) {
    this.id = id;
    this.filmProd = filmProd;
    this.name = name;
    this.genre = genre;
  }
}

class FilmProducer {
  int id;
  String titleName;
}

class Genre {
  int id;
  String name;

  public Genre(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

class FilmProducerFactory {
  int count = 1;

  public FilmProducer getFilmProducer(String name) {
    FilmProducer fp = new FilmProducer();
    fp.id = count++;
    fp.titleName = name;
    return fp;
  }
}