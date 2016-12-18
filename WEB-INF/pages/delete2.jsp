<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <h3>List of pictures</h3>

    <form action="/deleteall" method="post">

        <table class="MyController">


            <h1>Photos id are: ${photo_id}</h1>

            <tr>

                <td>${photo_id0}</td>

                <td><input type="checkbox" id="${photo_id0}" name="${name0}" value="${photo_id0}" style="position:absolute;">
                </td>

            </tr>
            <tr>

                <td>${photo_id1}</td>

                <td><input type="checkbox" id="${photo_id1}" name="${name1}" value="${photo_id1}" style="position:absolute;">
                </td>

            </tr>
            <tr>

                <td>${photo_id2}</td>

                <td><input type="checkbox" id="${photo_id2}" name="${name2}" value="${photo_id2}" style="position:absolute;">
                </td>

            </tr>
            <tr>

                <td>${photo_id3}</td>

                <td><input type="checkbox" id="${photo_id3}" name="${name3}" value="${photo_id3}" style="position:absolute;">
                </td>

            </tr>

        </table>

        <input type="submit" value="Del selected pics"/>

    </form>

</div>
</body>
</html>