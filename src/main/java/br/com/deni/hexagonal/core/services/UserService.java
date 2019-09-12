package br.com.deni.hexagonal.core.services;

import br.com.deni.hexagonal.core.domain.User;
import br.com.deni.hexagonal.core.domain.dto.UserDTO;
import br.com.deni.hexagonal.core.services.exception.ObjectNotFoundException;
import br.com.deni.hexagonal.adapters.SenderAdapter;
import br.com.deni.hexagonal.core.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private SenderAdapter sender;

    public User findById(Integer id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    public List<User> findAll(){
        return repo.findAll();
    }

    public User insert(User obj) {
        repo.save(obj);
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
        System.out.println(gson.toJson(obj));
        sender.send(gson.toJson(obj));
        return obj;
    }

    public void delete(Integer id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName());
    }
}
