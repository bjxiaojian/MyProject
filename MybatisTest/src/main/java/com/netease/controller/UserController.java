package com.netease.controller;

import com.netease.mapper.IUserMapper;
import com.netease.model.Article;
import com.netease.util.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/article")
public class UserController {
  @Autowired
  IUserMapper userMapper;

  @RequestMapping("/list")
  public ModelAndView listall() {
    List<Article> articles = userMapper.getUserArticles(1);
    ModelAndView mav = new ModelAndView("list");
    mav.addObject("articles", articles);
    return mav;
  }

  @RequestMapping("/pagelist")
  public ModelAndView pageList(HttpServletRequest request, HttpServletResponse response) {
    int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
    int pageSize = 3;
    if (currentPage <= 0) {
      currentPage = 1;
    }
    int currentResult = (currentPage - 1) * pageSize;

    System.out.println(request.getRequestURI());
    System.out.println(request.getQueryString());

    PageInfo page = new PageInfo();
    page.setShowCount(pageSize);
    page.setCurrentResult(currentResult);
    List<Article> articles = userMapper.selectArticleListPage(page, 1);

    System.out.println(page);

    int totalCount = page.getTotalResult();

    int lastPage = 0;
    if (totalCount % pageSize == 0) {
      lastPage = totalCount % pageSize;
    } else {
      lastPage = 1 + totalCount / pageSize;
    }

    if (currentPage >= lastPage) {
      currentPage = lastPage;
    }

    String pageStr = "";

    pageStr = String.format("<a href=\"%s\">上一页</a>    <a href=\"%s\">下一页</a>",
        request.getRequestURI() + "?page=" + (currentPage - 1), request.getRequestURI() + "?page=" + (currentPage + 1));


    //制定视图，也就是list.jsp
    ModelAndView mav = new ModelAndView("list");
    mav.addObject("articles", articles);
    mav.addObject("pageStr", pageStr);
    return mav;
  }
}