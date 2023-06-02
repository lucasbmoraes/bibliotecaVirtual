package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Model.Livro;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.LivroRepository;
import com.example.demo.Repository.UsuarioRepository;

@Controller
public class BibliotecaController {

    @Autowired
    UsuarioRepository repositoryUsuario;

    @Autowired
    LivroRepository repositoryLivro;

    // -------Tela de início------------

    @GetMapping("/paginaInicial")
    public String paginaInicial() {
        return "paginaInicial";
    }

    // REDIRECIONA PARA A HOME

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // VALIDAÇÃO DE LOGIN

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String senha) {
        Usuario user = repositoryUsuario.findByUsernameAndSenha(username, senha);
        if (user != null) {
            return "paginaInicial";
        }
        return "redirect:/index";
    }

    // RETORNA A TELA DE CADASTRO DE USUÁRIO

    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario() {
        return "cadastroUsuario";
    }

    // SALVA O USUÁRIO NO BANCO

    @PostMapping("/cadastroUsuario")
    public String salvarUsuario(Usuario usuario) {
        repositoryUsuario.save(usuario);
        return "redirect:/index";
    }

    // LISTA O USUÁRIO

    @GetMapping("/listaUsuario")
    public ModelAndView listaUsuario() {
        ModelAndView mv = new ModelAndView("listaUsuario");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = (ArrayList<Usuario>) repositoryUsuario.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    // EXCLUIR USUARIO

    @GetMapping("/excluirUsuario/{id}") // exluirUsuario/{id} - serve para buscar o id da URL do site
    public String excluir(@PathVariable("id") int id) { // ajuda a chamar o id da URL do site
        repositoryUsuario.deleteById(id); // deletar o que corresponde ao ID selecionado
        return "redirect:/listaUsuario"; // redireciona tudo para a lista novamente

    }

    // EDITAR USUARIO

    @GetMapping("/editarUsuario/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("cadastroUsuario");
        Usuario user = new Usuario();
        user = repositoryUsuario.findById(id).get();
        mv.addObject("usuario", user); // repository.findbyid puxa o id e manda para o
                                       // mv
        // .get serve para forçar puxar o id (tem que usar)

        return mv; // retorna o mv
    }

    // RETORNA A TELA DE CADASTRO DE LIVROS

    @GetMapping("cadastroLivro")
    public String cadastroLivro() {
        return "cadastroLivro";
    }

    // CADASTRA O LIVRO

    @PostMapping("/cadastroLivro")
    public String cadastraLivro(Livro livro) {
        if (livro.getAno_publicacao() == null) {
            return "";
        } else {
            repositoryLivro.save(livro);
        }
        return "redirect:/listaLivro";
    }

    // LISTA O LIVRO

    @GetMapping("/listaLivro")
    public ModelAndView listarLivro() {
        ModelAndView mv = new ModelAndView("listaLivro");
        List<Livro> livros = new ArrayList<>();
        livros = repositoryLivro.findAll();
        mv.addObject("livros", livros);
        return mv;
    }

    // EXCLUIR LIVRO

    @GetMapping("/excluirLivro/{id}") // Pega o id do livro pela URL
    public String excluirLivro(@PathVariable int id) // coloca na variavel 'id' de inteiro o id pego pela url
    {
        repositoryLivro.deleteById(id);// exclui livro o livro buscado pelo id
        return "redirect:/listaLivro";// redireciona para o listarLivro
    }

    // EDITAR LIVRO

    @GetMapping("/editarLivro/{id}") // Pega o id do livro pela URL
    public ModelAndView editarLivro(@PathVariable int id) // coloca na variavel 'id' de inteiro o id pego pela url
    {
        ModelAndView mv = new ModelAndView("cadastroLivro"); // cria uma cópia do cadastro do livro
        Livro livro = repositoryLivro.findById(id).get(); // Cria um objeto livro e adciona os valores pegados do banco
        mv.addObject("livro", livro); // incrementa o objeto livro no MV
        return mv; // retorna MV com o livro
    }

    // RESERVAR LIVRO

    @GetMapping("/reservarLivro/{id}") // Pega o id do livro pela URL
    public String reservarLivro(@PathVariable int id) // coloca na variavel 'id' de inteiro o id pego pela url
    {
        Livro livro = repositoryLivro.findById(id).get(); // Cria um objeto livro e adciona os valores pegados do banco
        if (!livro.isStatus()) {
            livro.setStatus(true);
            repositoryLivro.save(livro);
        } else {
            livro.setStatus(false);
            repositoryLivro.save(livro);
        }
        return "redirect:/listaLivro"; // retorna MV com o livro
    }
}
