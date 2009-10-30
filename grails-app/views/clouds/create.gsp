
<%@ page import="com.foo.sky.Clouds" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Clouds</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Clouds List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Clouds</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cloudsInstance}">
            <div class="errors">
                <g:renderErrors bean="${cloudsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tag">Tag:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cloudsInstance,field:'tag','errors')}">
                                    <input type="text" id="tag" name="tag" value="${fieldValue(bean:cloudsInstance,field:'tag')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
