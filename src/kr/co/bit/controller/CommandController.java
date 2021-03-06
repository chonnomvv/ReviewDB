package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.dao.MemberDAO;
import kr.co.bit.vo.MemberVO;

public class CommandController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
		System.out.println("123");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");
		cmd = cmd == null ? "" : cmd;
		System.out.println(cmd);
		request.setCharacterEncoding("UTF-8");
		String url = "./mvc/home.jsp";
		if (cmd.equals("regist")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String zip1 = request.getParameter("zip1");
			String zip2 = request.getParameter("zip2");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String tool = request.getParameter("tool");
			String project = request.getParameter("project");
			String[] langs = request.getParameterValues("lang");

			// MemberVO 데이터 클래스를 만들어서 인스턴스를 하나 생성
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPw(pw);
			name = new String(name.getBytes("iso-8859-1"), "UTF-8");
			vo.setName(name);
			// System.out.println(new String(name.getBytes(("iso-8859-1"),"UTF-8");
			vo.setEmail(email);
			vo.setZipcode(zip1 + "-" + zip2);
			vo.setAddr1(addr1);
			vo.setAddr2(addr2);
			vo.setTool(tool);
			vo.setProject(project);
			vo.setLangs(langs);

			MemberDAO dao = new MemberDAO();
			boolean flag = dao.insert(vo);
			System.out.println("입력완료");
			request.setAttribute("message", "success");
			// response.sendRedirect("storage.jsp");
			if (flag) {
				url = "./mvc/list.jsp";
			} else {
				// url = "./mvc/error.jsp";}
			}
		} else if (cmd.equals("viewRegist")) {
			url = "./mvc/regist_member.jsp";

		} else if (cmd.equals("search")) {
			url = "./mvc/search.jsp";
			request.setAttribute("id", request.getParameter("id")); 
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, resp);

	}

}
