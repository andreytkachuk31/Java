<html>
<head>
     <title>Upload image</title>
</head>
<body>
    <form method="POST" action="/upload/save" enctype="multipart/form-data">
        <tr>
            <th><label for="image">Profile image:</label></th>
            <td><input name="image" type="file"/>
            <td><input name="button" type="submit"/>
        </tr>
    </form>
</body>
</hmtl>