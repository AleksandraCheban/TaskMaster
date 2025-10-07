fun main() {
    val tasks = mutableListOf<Map<String, Any?>>()

    while (true) {
        println("\nВыберите действие:")
        println("1. Просмотреть задачи")
        println("2. Добавить задачу")
        println("3. Редактировать задачу")
        println("4. Удалить задачу")
        println("5. Выйти")
        print("Введите номер действия: ")

        when (readlnOrNull()) {
            "1" -> showTasks(tasks)
            "2" -> addTask(tasks)
            "3" -> editTask(tasks)
            "4" -> deleteTask(tasks)
            "5" -> return
            else -> println("Некорректный ввод. Попробуйте ещё раз.")
        }
    }
}

fun addTask(tasks: MutableList<Map<String, Any?>>) {
    println("\nДобавление новой задачи:")

    print("Введите название: ")
    val title = readlnOrNull() ?: return

    print("Введите категорию: ")
    val category = readlnOrNull() ?: return

    print("Введите приоритет (Низкий/Средний/Высокий): ")
    val priority = readlnOrNull() ?: return

    print("Введите дату выполнения (например, 2025-10-15): ")
    val dueDate = readlnOrNull() ?: return

    print("Введите описание (опционально): ")
    val description = readlnOrNull()

    val id = "task_${(Math.random() * 1000).toInt()}"
    val creationDate = "2025-10-07" // Можно заменить на текущую дату

    val task = mapOf(
        "id" to id,
        "title" to title,
        "category" to category,
        "priority" to priority,
        "dueDate" to dueDate,
        "description" to description,
        "creationDate" to creationDate,
        "isCompleted" to false
    )

    tasks.add(task)
    println("Задача успешно добавлена!")
}

fun showTasks(tasks: List<Map<String, Any?>>) {
    if (tasks.isEmpty()) {
        println("Список задач пуст.")
        return
    }

    println("\nСписок задач:")
    tasks.forEach { task ->
        println("ID: ${task["id"]}")
        println("Название: ${task["title"]}")
        println("Категория: ${task["category"]}")
        println("Приоритет: ${task["priority"]}")
        println("Дата выполнения: ${task["dueDate"]}")
        println("Описание: ${task["description"] ?: "Нет описания"}")
        println("Дата создания: ${task["creationDate"]}")
        println("Статус: ${if (task["isCompleted"] as? Boolean == true) "Выполнено" else "Активно"}")
        println("---")
    }
}

fun editTask(tasks: MutableList<Map<String, Any?>>) {
    print("Введите ID задачи для редактирования: ")
    val id = readlnOrNull() ?: return

    val taskIndex = tasks.indexOfFirst { it["id"] == id }
    if (taskIndex == -1) {
        println("Задача с ID $id не найдена.")
        return
    }

    val task = tasks[taskIndex].toMutableMap()

    println("Редактирование задачи:")
    print("Новое название (текущее: ${task["title"]}): ")
    task["title"] = readlnOrNull() ?: task["title"]

    print("Новая категория (текущая: ${task["category"]}): ")
    task["category"] = readlnOrNull() ?: task["category"]

    print("Новый приоритет (текущий: ${task["priority"]}): ")
    task["priority"] = readlnOrNull() ?: task["priority"]

    print("Новая дата выполнения (текущая: ${task["dueDate"]}): ")
    task["dueDate"] = readlnOrNull() ?: task["dueDate"]

    print("Новое описание (текущее: ${task["description"] ?: "Нет описания"}): ")
    task["description"] = readlnOrNull() ?: task["description"]

    tasks[taskIndex] = task
    println("Задача успешно обновлена!")
}

fun deleteTask(tasks: MutableList<Map<String, Any?>>) {
    print("Введите ID задачи для удаления: ")
    val id = readlnOrNull() ?: return

    val taskIndex = tasks.indexOfFirst { it["id"] == id }
    if (taskIndex == -1) {
        println("Задача с ID $id не найдена.")
        return
    }

    tasks.removeAt(taskIndex)
    println("Задача успешно удалена!")
}
