http://image.tmdb.org/t/p/original/

Документация api.themoviedb

Интерфейс - TheMovieDatabaseAPI
Логика - /logic/ApiLogic
Дата класс страницы - /items/MoviePageX
Дата класс одного фильма - /item/SingleMovie


метод - searchForMovies(query: String, language: Language)
Поиск фильма по названию(query), язык ответа(language) -
enum class Language(val lang: String) {
    RU("ru-RU"),
    EU("en-EU"),
}.

метод - findMovieById(movieId: Int, language: Language)
Поиск фильма по id(movieId), язык ответа(language).

метод - findMovieById(movieId: Int, language: Language)
Поиск фильма по id(movieId), язык ответа(language).

метод - topMovieWeek(language: Language)
Топ недели, язык ответа(language).


Пример вызова:
val temp = ApiLogic()
try {
    temp.findMovieById(20, ApiLogic.Language.RU)
} catch (error: Exception){
    print(error)
}