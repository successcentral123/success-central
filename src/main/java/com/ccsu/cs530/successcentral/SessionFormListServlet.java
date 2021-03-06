package com.ccsu.cs530.successcentral;

import com.ccsu.cs530.successcentral.model.SessionForm;
import com.ccsu.cs530.successcentral.service.CrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/session_form_list")
public class SessionFormListServlet extends HttpServlet {
    private CrudService crud = new CrudService();
    private int page;
    private final int perPageNum = 15;
    private final int perBlockNum = 10;
    int startPage =0;
    int endPage = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getSession().getAttribute("email") != null) {
            //if not admin, bounce to mentor dashboard page
            if (!req.getSession().getAttribute("isAdmin").equals("true")){
                String mentorName = (String) req.getSession().getAttribute("mentorFullName");




                String pageNum = req.getParameter("page");
                if(pageNum == null) page=1;
                else page = Integer.parseInt(pageNum);

                int firstRecord = (page -1) * perPageNum;
                int sessionCount = crud.countSessionForms();
                int pageCount = 1;

                if(sessionCount != 0){
                    pageCount = sessionCount / perPageNum + (sessionCount % perPageNum != 0 ? 1 : 0);

                    startPage = ((page-1)/perBlockNum)*perBlockNum+1;
                    endPage   = startPage + perBlockNum -1;

                    if(endPage >pageCount){
                        endPage = pageCount;
                    }
                }

                String search = (String)req.getParameter("search");
                String sortBy = (String)req.getParameter("sortBy");
                List<SessionForm> sessionforms = crud.getSessionFormsMentor(firstRecord, perPageNum, search, sortBy,mentorName);


                // populate the request object to send to the jsp
                req.setAttribute("pageCount", pageCount);
                req.setAttribute("sessionforms", sessionforms);
                req.setAttribute("sessionCount",sessionCount);
                req.setAttribute("perBlockNum", perBlockNum);
                req.setAttribute("startPage",startPage);
                req.setAttribute("endPage",endPage);
                req.setAttribute("page",page);
                req.setAttribute("search",search);
                req.setAttribute("sortBy",sortBy);
                req.getRequestDispatcher("session_form_list.jsp").forward(req, resp);
                return;




//                resp.sendRedirect("session_form_list");
//                return;
            }
            // paging
            String pageNum = req.getParameter("page");
            if(pageNum == null) page=1;
            else page = Integer.parseInt(pageNum);

            int firstRecord = (page -1) * perPageNum;
            int sessionCount = crud.countSessionForms();
            int pageCount = 1;

            if(sessionCount != 0){
                pageCount = sessionCount / perPageNum + (sessionCount % perPageNum != 0 ? 1 : 0);

                startPage = ((page-1)/perBlockNum)*perBlockNum+1;
                endPage   = startPage + perBlockNum -1;

                if(endPage >pageCount){
                    endPage = pageCount;
                }
            }

            String search = (String)req.getParameter("search");
            String sortBy = (String)req.getParameter("sortBy");
            List<SessionForm> sessionforms = crud.getSessionForms(firstRecord, perPageNum, search, sortBy);


            // populate the request object to send to the jsp
            req.setAttribute("pageCount", pageCount);
            req.setAttribute("sessionforms", sessionforms);
            req.setAttribute("sessionCount",sessionCount);
            req.setAttribute("perBlockNum", perBlockNum);
            req.setAttribute("startPage",startPage);
            req.setAttribute("endPage",endPage);
            req.setAttribute("page",page);
            req.setAttribute("search",search);
            req.setAttribute("sortBy",sortBy);
            req.getRequestDispatcher("session_form_list.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }
}
