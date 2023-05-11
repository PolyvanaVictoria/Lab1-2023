package com.example.lab1

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import java.lang.Boolean
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDateTime
import kotlin.Throws


class MyServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val key = request.getParameter("key")
        val value = request.getParameter("value").toInt()
        val test = Boolean.parseBoolean(request.getParameter("test"))
        if (test) {
            response.contentType = "text/plain"
            val out = response.writer
            out.println("Confirmation message")
        } else {
            val fileName = "database.txt"
            val line = key.repeat(value) + " " + LocalDateTime.now()
            Files.write(Paths.get(fileName), listOf(line), StandardCharsets.UTF_8, StandardOpenOption.APPEND)
            val lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)
            val html = StringBuilder()
            html.append("<html><body><ul>")
            for (entry in lines) {
                html.append("<li>").append(entry).append("</li>")
            }
            html.append("</ul></body></html>")
            response.contentType = "text/html"
            val out = response.writer
            request.setAttribute("lines", lines)
            request.getRequestDispatcher("results.jsp").forward(request, response)
        }

    }
}

