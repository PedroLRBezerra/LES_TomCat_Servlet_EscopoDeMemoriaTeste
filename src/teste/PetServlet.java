package teste;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/petServlet")

public class PetServlet extends HttpServlet {
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Pet> lista = (List<Pet>)getServletContext().getAttribute("LISTA");
		if(lista==null) {
			System.out.println("Criando a Lista");
			lista =  new ArrayList<>();
			getServletContext().setAttribute("LISTA", lista);
		}
		
		
		String nome = req.getParameter("nome");
		String raca = req.getParameter("raca");
		String nasc = req.getParameter("nascimento");
		
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		Pet p = new Pet();
		p.setNome(nome);
		p.setRaca(raca);
		p.setNascimento(LocalDate.parse(nasc, formater));
		
		lista.add(p);
		
		String msg = String.format("O pet %s foi cadastrado com sucesso",nome);
		HttpSession session = req.getSession();
		session.setAttribute("MENSAGEM",msg);
		
		resp.sendRedirect("./Pet.jsp");
	}
	
}
