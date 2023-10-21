package ru.kazakov.labaratory.library.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MenuController {

    private final String manual = """
            <h1 id="-">Приложение-помощник библиотекаря</h1>
            <h2 id="-">Требуемое для запуска ПО</h2>
            <ul>
            <li>Docker@^24.0.5</li>
            <li><h2 id="-">Запуск приложения</h2>
            </li>
            <li><p>Открыть репозиторий через cmd</p>
            </li>
            <li><p>Установить требуемые зависимости</p>
            <pre><code>npm <span class="hljs-keyword">install</span>
            </code></pre></li>
            <li><p>Запустить Docker</p>
            <pre><code>docker compose up postgres <span class="hljs-_">-d</span>
            </code></pre><p>Адрес - <strong>localhost:5432</strong>.</p>
            </li>
            </ul>
            <p>Таблицы <strong>readers</strong> и <strong>books</strong> при инициализации имеют по 2 записи для проведения интеграционных тестов.</p>
            <ol>
            <li>Запустить сервер Node.js<pre><code><span class="hljs-keyword">node</span> <span class="hljs-title">bin</span>\\www
            </code></pre>Адрес - <strong>localhost:3000</strong></li>
            </ol>
            <h2 id="-">Запуск интеграционных тестов</h2>
            <p>Команда для запуска тестов:</p>
            <pre><code>npm <span class="hljs-keyword">run</span><span class="bash"> <span class="hljs-built_in">test</span></span>
            </code></pre><h3 id="-">Описание тестов</h3>
            <ol>
            <li>Тесты книг и читателей<ol>
            <li>Добавление книги</li>
            <li>Добавление книги, которая уже была добавлена</li>
            <li>Поиск книги с неверным <strong>id</strong></li>
            <li>Добавление читателя</li>
            <li>Поиск читателя с неверным <strong>id</strong></li>
            </ol>
            </li>
            <li>Тесты событий<ol>
            <li>Поиск события с неверным <strong>id</strong></li>
            <li>Добавление события &quot;<strong>TAKE</strong>&quot;</li>
            <li>Добавление события &quot;<strong>TAKE</strong>&quot;, когда количество требуемой книги равно 0</li>
            <li>Добавление события &quot;<strong>RETURN</strong>&quot;</li>
            <li>Добавление события &quot;<strong>TAKE</strong>&quot; с неверными <strong>id</strong> книги и читателя</li>
            </ol>
            </li>
            <li>Тесты лучшей книги и лучшего читателя<ol>
            <li>Поиск лучшей книги</li>
            <li>Поиск лучшего читателя</li>
            <li>Поиск лучшей книги с какого-то времени</li>
            <li>Поиск лучшего читателя с какого-то времени</li>
            </ol>
            </li>
            <li>Тесты метода &quot;<strong>DELETE</strong>&quot;<ol>
            <li>Удаление события</li>
            <li>Удаление читателя</li>
            <li>Удаление книги</li>
            </ol>
            </li>
            </ol>
            <h2 id="-">Работа с сервером</h2>
            <p>Приложение состоит из 3-х таблиц (<strong>readers, books, events</strong>), взаимодействие с ними представлено в виде отправки запросов на соответствующие адреса</p>
            <h3 id="-">ЧИТАТЕЛИ</h3>
            <p><code>name - имя, surname - фамилия, from - от, to - до</code></p>
            <ul>
            <li><h4 id="get">GET</h4>
            </li>
            </ul>
            <p>Вывод всех читателей из таблицы.</p>
            <blockquote>
            <p>/readers</p>
            </blockquote>
            <p>Вывод всех читателей из таблицы, содержащих в соответствующих полях значения параметров <strong>name</strong> и <strong>surname</strong> без учета регистра.</p>
            <blockquote>
            <p>/readers?<strong>name</strong>=XXX&amp;<strong>surname</strong>=XXX</p>
            </blockquote>
            <p>Вывод читателя по конкретному <strong>id</strong>.</p>
            <blockquote>
            <p>/readers/{<strong>id</strong>}</p>
            </blockquote>
            <p>Вывод самого читающего читателя за конкретный отрезок времени (от <strong>from</strong> до <strong>to</strong>). Все параметры опциональны, при их отсутствии выводится самый читающий читатель за все время.</p>
            <p><code>Время указывается в формате yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ (пример: 2023-07-01T14:59:55.711+0000)</code></p>
            <blockquote>
            <p>/readers/top?<strong>from</strong>=XXX&amp;<strong>to</strong>=XXX</p>
            </blockquote>
            <ul>
            <li><h4 id="post">POST</h4>
            </li>
            </ul>
            <p><strong>При добавлении читатель получает случайный id</strong></p>
            <p>Добавление читателя с указанными значениями <strong>name</strong> и <strong>surname</strong></p>
            <blockquote>
            <p>/readers?<strong>name</strong>=XXX&amp;<strong>surname</strong>=XXX</p>
            </blockquote>
            <ul>
            <li><h4 id="put">PUT</h4>
            </li>
            </ul>
            <p>Редактирование <strong>name</strong> и/или <strong>surname</strong> пользователя с конкретным <strong>id</strong>. Все параметры опциональны.</p>
            <blockquote>
            <p>/readers/{id}?<strong>name</strong>=XXX&amp;<strong>surname</strong>=XXX</p>
            </blockquote>
            <ul>
            <li><h4 id="delete">DELETE</h4>
            </li>
            </ul>
            <p>Удаление читателя по конкретному <strong>id</strong>.</p>
            <blockquote>
            <p>/readers/{<strong>id</strong>}</p>
            </blockquote>
            <h3 id="-">КНИГИ</h3>
            <p><code>title - название, author - автор, quantity - количество, from - от, to - до</code></p>
            <ul>
            <li><h4 id="get">GET</h4>
            </li>
            </ul>
            <p>Вывод всех книг из таблицы.</p>
            <blockquote>
            <p>/books</p>
            </blockquote>
            <p>Вывод всех книг из таблицы, содержащих в соответствующих полях значения параметров <strong>title</strong> и <strong>author</strong> без учета регистра.</p>
            <blockquote>
            <p>/books?<strong>title</strong>=XXX&amp;<strong>author</strong>=XXX</p>
            </blockquote>
            <p>Вывод книги по конкретному <strong>id</strong>.</p>
            <blockquote>
            <p>/books/{<strong>id</strong>}</p>
            </blockquote>
            <p>Вывод самой популярной книги за конкретный отрезок времени (от <strong>from</strong> до <strong>to</strong>). Все параметры опциональны, при их отсутствии выводится самая популярная книга за все время.</p>
            <p><code>Время указывается в формате yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ (пример: 2023-07-01T14:59:55.711+0000)</code></p>
            <blockquote>
            <p>/books/top?<strong>from</strong>=XXX&amp;<strong>to</strong>=XXX</p>
            </blockquote>
            <ul>
            <li><h4 id="post">POST</h4>
            </li>
            </ul>
            <p><strong>При добавлении книга получает случайный id</strong></p>
            <p>Добавление книги с указанными значениями <strong>title</strong>, <strong>author</strong> и <strong>quantity</strong>. Параметр <strong>quantity</strong> опциональный, значение по умолчанию равно 1.</p>
            <blockquote>
            <p>/books?<strong>title</strong>=XXX&amp;<strong>author</strong>=XXX&amp;<strong>quantity</strong>=XXX</p>
            </blockquote>
            <ul>
            <li><h4 id="put">PUT</h4>
            </li>
            </ul>
            <p>Редактирование <strong>title</strong> и/или <strong>author</strong> и/или <strong>quantity</strong> книги с конкретным <strong>id</strong>. Все параметры опциональны.</p>
            <blockquote>
            <p>/books/{id}?<strong>title</strong>=XXX&amp;<strong>author</strong>=XXX&amp;<strong>quantity</strong>=XXX</p>
            </blockquote>
            <ul>
            <li><h4 id="delete">DELETE</h4>
            </li>
            </ul>
            <p>Удаление книги по конкретному <strong>id</strong>.</p>
            <blockquote>
            <p>/books/{<strong>id</strong>}</p>
            </blockquote>
            <h3 id="-">СОБЫТИЯ</h3>
            <p><code>action (TAKE/RETURN) - действие (взял/вернул), bookid - идентификатор книги, readerid - идентификатор читателя, time - время события</code></p>
            <ul>
            <li><h4 id="get">GET</h4>
            </li>
            </ul>
            <p>Вывод всех событий из таблицы.</p>
            <blockquote>
            <p>/events</p>
            </blockquote>
            <p>Вывод события по конкретному <strong>id</strong>.</p>
            <blockquote>
            <p>/events/{<strong>id</strong>}</p>
            </blockquote>
            <ul>
            <li><h4 id="post">POST</h4>
            </li>
            </ul>
            <p><strong>При добавлении событие получает случайный id, time равно дате и времени создания события</strong></p>
            <p>Добавление события с указанными значениями <strong>bookid</strong>, <strong>readerid</strong>, <strong>type</strong>. </p>
            <blockquote>
            <p>/events?<strong>bookid</strong>=XXX&amp;<strong>readerid</strong>=XXX&amp;<strong>type</strong>=TAKE/RETURN</p>
            </blockquote>
            <ul>
            <li><h4 id="delete">DELETE</h4>
            </li>
            </ul>
            <p>Удаление события по конкретному <strong>id</strong>.</p>
            <blockquote>
            <p>/events/{<strong>id</strong>}</p>
            </blockquote>
            """;
    @GetMapping
    public String getMenu() {
        return manual;
    }
}
