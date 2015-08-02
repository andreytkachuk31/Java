<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/dwr/interface/Remote.js"></script>
    <script type="text/javascript" src="/dwr/engine.js"></script>
    <script type="text/javascript">
        Remote.getData(42, {
            callback: function (str) {
                alert(str);
            }});
    </script>
    <title></title>
</head>
<body>

</body>
</html>