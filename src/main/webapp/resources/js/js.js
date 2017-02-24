var id0;
var arr = {}; //массив для заполнения окна редактирования
var arr_serch = {}; //выходной массив из поиска
var flag_add = true; //флаг для регулирования заполнения списка категорий в окне создания
var flag_edit = true; //флаг для регулирования заполнения списка категорий в окне редактирования

//поиск
function search() {
    var str = $("#search_field").val();
    $("#search_field").val("");

    if(str.length > 3){
        //Поиск по заголовку
        if ($("input:radio:checked").val() == 1 ){
            $.ajax({
                url:  "getNewsByTitle?title=" + str ,
                type: 'GET',
                dataType: "json",
                success: function(data){
                    if(data != 0){
                        $('#content').html("");
                        arr_serch = {};
                        $.each(data, function(index,news){
                            arr_serch[news.id] = {'title': news.title ,
                                "content":news.content,
                                "category" : news.category
                            };

                            $('#content').append(
                                "<div>" +
                                "<h1>" + news.title + "</h1>" +
                                "<p>" + news.content + "</p>" +
                                "<p style='color: #8c8c8c;'>Категория: " + news.category + "</p>" +
                                "<p style='color: #1b6d85;'> Дата публикации: " + news.date + "</p>" +
                                "<button class='btn btn-default' onclick='edit(" + news.id + ")'>Редактировать</button>" +
                                "<button id='del' class='btn btn-default' onclick='del("+news.id+")' >Удалить</button>"
                                + "</div>"
                            );
                        });
                    }else {
                        alert("Не верный формат поиска!");
                    }
                },
                fail: function(){
                    alert("Something was wrong during get News");
                }
            });
        }
        //Поиск по содержимому
        if ($("input:radio:checked").val() == 2){
            $.ajax({
                url:  "getContentLike?content=" + str ,
                type: 'GET',
                dataType: "json",
                success: function(data){
                    if(data != 0) {
                        $('#content').html("");
                        arr_serch = {};
                        $.each(data, function (index, news) {
                            arr_serch[news.id] = {
                                'title': news.title,
                                "content": news.content,
                                "category": news.category
                            };

                            $('#content').append(
                                "<div>" +
                                "<h1>" + news.title + "</h1>" +
                                "<p>" + news.content + "</p>" +
                                "<p style='color: #8c8c8c;'>Категория: " + news.category + "</p>" +
                                "<p style='color: #1b6d85;'> Дата публикации: " + news.date + "</p>" +
                                "<button class='btn btn-default' onclick='edit(" + news.id + ")'>Редактировать</button>" +
                                "<button id='del' class='btn btn-default' onclick='del(" + news.id + ")' >Удалить</button>"
                                + "</div>"
                            );
                        });
                    }else {
                        alert("Не верный формат поиска!");
                    }
                },
                fail: function(){
                    alert("Something was wrong during get News");
                }
            });
        }
        //Поиск по категории
        if ($("input:radio:checked").val() == 3){
            $.ajax({
                url:  "getCategoryLike?category=" + str ,
                type: 'GET',
                dataType: "json",
                success: function(data){
                    if(data != 0) {
                        $('#content').html("");
                        arr_serch = {};
                        $.each(data, function (index, news) {
                            arr_serch[news.id] = {
                                'title': news.title,
                                "content": news.content,
                                "category": news.category
                            };

                            $('#content').append(
                                "<div>" +
                                "<h1>" + news.title + "</h1>" +
                                "<p>" + news.content + "</p>" +
                                "<p style='color: #8c8c8c;'> Категория: " + news.category + "</p>" +
                                "<p style='color: #1b6d85;'>Дата публикации: " + news.date + "</p>" +
                                "<button class='btn btn-default' onclick='edit(" + news.id + ")'>Редактировать</button>" +
                                "<button id='del' class='btn btn-default' onclick='del(" + news.id + ")' >Удалить</button>"
                                + "</div>"
                            );
                        });
                    }else {
                        alert("Не верный формат поиска!");
                    }
                },
                fail: function(){
                    alert("Something was wrong during get News");
                }
            });
        }

    }else {
        alert("Не верный формат поиска!");
        getAllNews();
    }
}


function showAddNews() {
    if(flag_add == true){
        $('#cat_selection1').append($('<option>', {
            value: "---",
            text : "---"
        }));
        $.each(arr, function (i, item) {
            $('#cat_selection1').append($('<option>', {
                value: item.category,
                text : item.category
            }));
        });
        flag_add = false;
    }
    $("#newNews").modal('show');
}

//получение полного списка статей
function getAllNews() {
    $('#content').html("");

    $.getJSON("/post/list",function (data) {
        $.each(data, function(index,news){
            arr[news.id] = {'title': news.title ,
                "content":news.content,
                "category" : news.category
            };
            $('#content').append(
                "<div>" +
                    "<h1>" + news.title + "</h1>" +
                    "<p>" + news.content + "</p>" +
                    "<p style='color: #8c8c8c;'>Категория: " + news.category + "</p>" +
                    "<p style='color: #1b6d85;'> Дата публикации: " + news.date + "</p>" +
                    "<button class='btn btn-default' onclick='edit(" + news.id + ")'>Редактировать</button>" +
                    "<button id='del' class='btn btn-default' onclick='del("+news.id+")' >Удалить</button>"
                + "</div>"
            );
        });
    });
}

//создание статьи
function saveNews(){
    $.ajax({
        url:  "create?title=" + $("#new_news_name").val()
        + "&content="
        + $("#new_news_content").val()
        + "&category=" + $("#new_news_category").val() ,
        type: 'POST',
        success: function(){
            $("#newNews").modal("hide");
            getAllNews();
            $("#new_news_name").val("");
            $("#new_news_content").val("");
            $("#new_news_category").val("");

        },
        fail: function(){
            alert("Something was wrong during save News");
        }
    });
    flag_edit = true;
    flag_add= true;
    $('#cat_selection1').html("");
    $('#cat_selection').html("");
}


//редактирование статьи
function editNews(){
    $.ajax({
        url:  "save?id=" + id0
        + "&title=" + $("#news_name").val()
        + "&content="
        + $("#news_content").val()
        + "&category=" + $("#news_category").val() ,
        type: 'PUT',
        success: function(){
            $("#editNews").modal("hide");
            getAllNews();
            $("#news_name").val("");
            $("#news_content").val("");
            $("#news_category").val("");
        },
        fail: function(){
            alert("Something was wrong during save News");
        }
    });
    flag_edit = true;
    flag_add= true;
    $('#cat_selection1').html("");
    $('#cat_selection').html("");
}

//выпадающий список категори в окне редактирования
function getCategory() {
    if (flag_edit == true){
        $.each(arr, function (i, item) {
            $('#cat_selection').append($('<option>', {
                value: item.category,
                text : item.category
            }));
        });
        flag_edit = false;
    }

}

//заполнение полей в форме редактирования новости
function edit(id) {
    id0 = id;
    getCategory();
    $("#news_title").html("<h1> Редактирование "+ arr[id0].title +" </h1>")
    $("#news_name").val(arr[id].title);
    $("#news_category").val(arr[id].category);
    $("#news_content").val(arr[id].content);
    $("#editNews").modal('show');

}

function del(id) {
    id0 = id;
    showDel();
}

//отображение модального окна удаления
function showDel() {
    $("#del_news_title").html("<h1> Вы уверенны что хотите удалить "+ arr[id0].title +"? </h1>")
    $("#delNews").modal('show');
}

//удаление новости
function deleteNews() {
    $.ajax({
        url:  "/post/delete?id=" + id0 ,
        type: 'DELETE',
        success: function(){
            $("#delNews").modal('hide');
            getAllNews();
        },
        fail: function(){
            alert("Something was wrong during delete News");
        }
    });
    flag_edit = true;
    flag_add= true;
    $('#cat_selection1').html("");
    $('#cat_selection').html("");
}

//action на выбор категории из выпадающего списка в окне добавления
function changeCatNew(){
    $('#cat_selection1').change(function() {
        $('#new_news_category').val($('#cat_selection1 option:selected').val());
    });
}

//action на выбор категории из выпадающего списка в окне редактирования
function changeCatEdit(){

    $('#cat_selection').change(function() {
        $('#news_category').val($('#cat_selection option:selected').val());
    });
}