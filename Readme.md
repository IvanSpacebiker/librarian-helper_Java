# Приложение-помощник библиотекаря
## Требуемое для запуска ПО
* Docker@^24.0.5

## Запуск приложения

Открыть в терминале папку **src/main/docker** и выполнить команду:
```
docker-compose up 
```


## Запуск интеграционных тестов

### Описание тестов
1. Тесты книг и читателей
   1. Добавление книги
   2. Добавление книги, которая уже была добавлена
   3. Поиск книги с неверным **id**
   4. Добавление читателя
   5. Поиск читателя с неверным **id**
2. Тесты событий
   1. Поиск события с неверным **id**
   2. Добавление события "**TAKE**"
   3. Добавление события "**TAKE**", когда количество требуемой книги равно 0
   4. Добавление события "**RETURN**"
   5. Добавление события "**TAKE**" с неверными **id** книги и читателя
3. Тесты лучшей книги и лучшего читателя
   1. Поиск лучшей книги
   2. Поиск лучшего читателя
   3. Поиск лучшей книги с какого-то времени
   4. Поиск лучшего читателя с какого-то времени
4. Тесты метода "**DELETE**"
   1. Удаление события
   2. Удаление читателя
   3. Удаление книги

    
## Работа с сервером
Приложение состоит из 3-х таблиц (**readers, books, events**), взаимодействие с ними представлено в виде отправки запросов на соответствующие адреса

### ЧИТАТЕЛИ
`name - имя, surname - фамилия, from - от, to - до`


* #### GET

Вывод всех читателей из таблицы.

> /readers

Вывод всех читателей из таблицы, содержащих в соответствующих полях значения параметров **name** и **surname** без учета регистра.

> /readers?**name**=XXX&**surname**=XXX

Вывод читателя по конкретному **id**.

> /readers/{**id**}

Вывод самого читающего читателя за конкретный отрезок времени (от **from** до **to**). Все параметры опциональны, при их отсутствии выводится самый читающий читатель за все время.

`Время указывается в формате yyyy-MM-dd'T'HH:mm:ss.SSSZ (пример: 2023-07-01T14:59:55.711+0000)`
> /readers/top?**from**=XXX&**to**=XXX


* #### POST

**При добавлении читатель получает случайный id**

Добавление читателя с указанными значениями **name** и **surname**

> /readers?**name**=XXX&**surname**=XXX


* #### PUT

Редактирование **name** и/или **surname** пользователя с конкретным **id**. Все параметры опциональны.

> /readers/{id}?**name**=XXX&**surname**=XXX


* #### DELETE

Удаление читателя по конкретному **id**.

> /readers/{**id**}



### КНИГИ
`title - название, author - автор, quantity - количество, from - от, to - до`


* #### GET

Вывод всех книг из таблицы.

> /books

Вывод всех книг из таблицы, содержащих в соответствующих полях значения параметров **title** и **author** без учета регистра.

> /books?**title**=XXX&**author**=XXX

Вывод книги по конкретному **id**.

> /books/{**id**}

Вывод самой популярной книги за конкретный отрезок времени (от **from** до **to**). Все параметры опциональны, при их отсутствии выводится самая популярная книга за все время.

`Время указывается в формате yyyy-MM-dd'T'HH:mm:ss.SSSZ (пример: 2023-07-01T14:59:55.711+0000)`

> /books/top?**from**=XXX&**to**=XXX


* #### POST

**При добавлении книга получает случайный id**

Добавление книги с указанными значениями **title**, **author** и **quantity**. Параметр **quantity** опциональный, значение по умолчанию равно 1.

> /books?**title**=XXX&**author**=XXX&**quantity**=XXX


* #### PUT

Редактирование **title** и/или **author** и/или **quantity** книги с конкретным **id**. Все параметры опциональны.

> /books/{id}?**title**=XXX&**author**=XXX&**quantity**=XXX



* #### DELETE

Удаление книги по конкретному **id**.

> /books/{**id**}


### СОБЫТИЯ
`action (TAKE/RETURN) - действие (взял/вернул), bookid - идентификатор книги, readerid - идентификатор читателя, time - время события`

* #### GET

Вывод всех событий из таблицы.

> /events


Вывод события по конкретному **id**.

> /events/{**id**}


* #### POST

**При добавлении событие получает случайный id, time равно дате и времени создания события**

Добавление события с указанными значениями **bookid**, **readerid**, **type**. 

> /events?**bookid**=XXX&**readerid**=XXX&**type**=TAKE/RETURN


* #### DELETE

Удаление события по конкретному **id**.

> /events/{**id**}




