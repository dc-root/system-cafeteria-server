package br.com.diego.Lanchonete;

@Controller
public class MeuPrimeiroController {
    @GetMapping("/oi") // caminho para chegar no método
    @ResponseBody // indica o que vai ser devolvido
    public String hello(){
        return "Oi, tudo bem?";
    }
}
