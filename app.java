import java.util.ArrayList;

public class app {
  public static void main(String[] args) {
    Infrastructure infrastructure = new Infrastructure();

    System.out.println(infrastructure.getAllInfo(1));
    System.out.println(infrastructure.getAllInfo(2));
    System.out.println(infrastructure.getAllInfo(3));
    System.out.println(infrastructure.getAllInfo(4));

    infrastructure.searchCinema();
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
    Cinema c1 = new Cinema(1, "Челюсти", 1, 1);
    Cinema c2 = new Cinema(2, "Жизнь на двоих", 2, 2);
    Cinema c3 = new Cinema(3, "Один дома", 3, 4);
    Cinema c4 = new Cinema(4, "Бэтмен", 4, 3);

    db.films.add(c1);
    db.films.add(c2);
    db.films.add(c3);
    db.films.add(c4);

    db.genres.add(new Genre(1, "Ужасы"));
    db.genres.add(new Genre(2, "Драма"));
    db.genres.add(new Genre(3, "Комедия"));
    db.genres.add(new Genre(4, "Фэнтези"));
    FilmProducerFactory pf = new FilmProducerFactory();
    db.addFilmProducer(pf.getFilmProducer("Paramount"));
    db.addFilmProducer(pf.getFilmProducer("Disney»"));
    db.addFilmProducer(pf.getFilmProducer("XXI centure"));
    db.addFilmProducer(pf.getFilmProducer("Pixel"));

    return db;
  }

  public void searchCinema(){
    boolean check = true;
    input scanner = new input();
    String text = "";
    while(check){
        text = scanner.inputString("Введте запрос по названию фильма или его части, для завершения поиска введите -1");
        if(text.equals("-1")) check = false;
        else{
            ArrayList<Cinema> movies = new ArrayList<>();
            for (Cinema cinema : db.films) {
                if (cinema.name.toLowerCase().indexOf(text.toLowerCase()) != -1){
                    movies.add(cinema);
                }
            }
            if (movies.size() == 0) System.out.println("Не найдено ни одного совпадения");
            else{
                System.out.println("Совпадения с запросом: ");
                for (Cinema cinema : movies) {
                    System.out.println(getAllInfo(cinema.id));
                }
            }
        }
    }
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