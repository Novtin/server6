package application.controller;

import application.DAO.ClientDAO;
import application.entities.Client;
import application.repositories.ClientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private final ClientRepository clientRepository;

    @Autowired
    public MainController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/")
    public String menuGet(){
        return "index";
    }

    @GetMapping("/clients")
    public String clientsGet(Model model){
        model.addAttribute("clients", clientRepository.findTop25AllByOrderByClientId());
        return "table";
    }

    @GetMapping("/delete")
    public String deleteGet(Model model){
        model.addAttribute("clients", clientRepository.findTop25AllByOrderByClientId());
        return "inputId";
    }

    @DeleteMapping("/delete")
    public String deletePost(@RequestParam(value = "inputId")
                                    @NotBlank @Pattern(regexp = "\\d+") String deleteId,
                                    Model model) {
        try {
            Optional<Client> client = clientRepository.findById(Integer.parseInt(deleteId));
            if (client.isEmpty()){
                throw new IndexOutOfBoundsException();
            } else {
                client.ifPresent(clientRepository::delete);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            model.addAttribute("errorMessage", "Введите корректный id");
            model.addAttribute("glassesList",  clientRepository.findTop25AllByOrderByClientId());
            return "inputId";
        }
        return "redirect:/";
    }

    @GetMapping("/save")
    public String addGet(Model model) {
        model.addAttribute("client", new Client());
        return "save";
    }

    @PostMapping("/save")
    public String addPost(@Valid @ModelAttribute("client") Client client,
                                 BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Введите корректные данные");
            return "save";
        }
        Optional<Client> clientFromBD = clientRepository.findById(client.getClientId());
        if (clientFromBD.isEmpty()) {
            clientRepository.save(client);
        } else {
            model.addAttribute("errorMessage", "Введите корректные данные");
            return "save";
        }
        return "redirect:/";
    }

}
