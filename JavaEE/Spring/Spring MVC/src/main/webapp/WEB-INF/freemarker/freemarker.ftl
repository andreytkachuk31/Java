<html>
<head>
    <title>FreeMarker</title>
</head>
<body>
    <h2>FreeMarker:</h2>
    <a href="/">Add rant</a><br/>
    <a href="/">Register new motorist</a><br/>
    <ul>
        <#list rants as rant>
            <li>
                ${rant}
            </li>
        </#list>
    </ul>
</body>
</html>