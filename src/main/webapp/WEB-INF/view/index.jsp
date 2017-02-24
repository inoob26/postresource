<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
  <head>
      <title>News Resource</title>
      <link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap-theme.min.css">
      <link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
      <script type="text/javascript" src="../../resources/js/js.js"></script>
      <meta charset="UTF-8">
  </head>
  <body onload="getAllNews()">
  <div class="container">
    <div class="col-lg-9">
        <!--         Строка поиска -->
        <br>
          <div class="input-group">
              <label class="radio-inline"><input type="radio" id="search_v1" checked name="searchtype" value="1">Поиск по названию</label>
              <label class="radio-inline"><input type="radio" id="search_v2" name="searchtype" value="2">Поиск по содержимому новости</label>
              <label class="radio-inline"><input type="radio" id="search_v3" name="searchtype" value="3">Поиск по категории</label>
              <input id="search_field" type="text" class="form-control" placeholder="Введите строку более 3х символов для поиска...">
            <div class="input-group-btn">
              <button id="search_btn" class="btn btn-default" onclick="search()">
                <i class="glyphicon glyphicon-search"></i>
              </button>
            </div>
          </div>
        <!--         Строка поиска -->

        <br>
        <button class="btn btn-info" onclick="showAddNews()">Добавить Новость</button>
      <div id="content">
      </div>
        <br>
      <div id="modal_editNews">
        <div class='modal fade' id='editNews'>
          <div class='modal-dialog'>
            <div class='modal-content'>
              <div class='modal-header'>
                <div id='news_title'></div>
              </div>
              <div class='modal-body'>
                <form>
                  <div class='form-group'>
                      <label for='news_name'>Заголовок </label>
                      <input type='text' id='news_name' class='form-control' />
                      <label for='news_category'>Категория</label>
                      <input type='text' id='news_category' class='form-control' />
                      <select class="form-control" onchange="changeCatEdit()" id="cat_selection">
                          <!--<option>---</option>-->
                      </select>
                      <label for="news_content">Контент</label>
                      <textarea id="news_content" rows="10" class="form-control" ></textarea>
                  </div>
                </form>
              </div>
              <div class='modal-footer'>
                <button type='submit' class='btn btn-default' onclick="editNews()">Сохранить</button>
                <button type='button' class='btn btn-default' data-dismiss='modal'>Выход</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="modal_newNews">
        <div class='modal fade' id='newNews'>
          <div class='modal-dialog'>
            <div class='modal-content'>
              <div class='modal-header'>
                <div id='new_news_title'><h3>Добавление новой Новости </h3></div>
              </div>
              <div class='modal-body'>
                <form>
                  <div class='form-group'>
                    <label for='new_news_name'>Заголовок </label>
                    <input type='text' id='new_news_name' class='form-control' />
                    <label for='new_news_category'>Категория</label>
                    <input type='text' id='new_news_category' class='form-control' />
                      <select class="form-control" onchange="changeCatNew()" id="cat_selection1">
                          <!--<option>---</option>-->
                      </select>
                    <label for="new_news_content">Контент</label>
                    <textarea id="new_news_content" rows="10" class="form-control" ></textarea>
                  </div>
                </form>
              </div>
              <div class='modal-footer'>
                <button type='submit' class='btn btn-default' onclick="saveNews()">Сохранить</button>
                <button type='button' class='btn btn-default' data-dismiss='modal'>Выход</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="modal_delNews">
        <div class='modal fade' id='delNews'>
          <div class='modal-dialog'>
            <div class='modal-content'>
              <div class='modal-body'>
                <form>
                  <div class='form-group'>
                    <div id='del_news_title'></div>
                  </div>
                </form>
              </div>
              <div class='modal-footer'>
                <button type='submit' class='btn btn-default' id="save_data" onclick="deleteNews()">YES</button>
                <button type='button' class='btn btn-default' data-dismiss='modal'>NO</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="../../resources/js/jquery-3.1.1.min.js"></script>
  <script type="text/javascript" src="../../resources/js/bootstrap.min.js"></script>
  </body>
</html>
