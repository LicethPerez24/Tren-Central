package com.apiTren.crudJava.services;

import com.apiTren.crudJava.models.UsersModel;
import com.apiTren.crudJava.repositories.IdUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
// en los servicios creamos los metodos a utilizar en el controlador
//se llama al repo para poder conectar con la base de datos
public class UserService {

    //injeccion de dependencias
    @Autowired
    IdUserRepository userRepository;

    //metodo que viene jparepository el cual dentro de el trae metodos como findall
    // para buscar todos los datos y mostrarlos a traves del array list
    public ArrayList<UsersModel> getUsers() {
        return (ArrayList<UsersModel>) userRepository.findAll();
    }

    //guardamos los ususarios
    public UsersModel saveUser(UsersModel user) {
        return userRepository.save(user);
    }

    /// este tipo OPTIONAL puede devolver algo espeifico o null
    public Optional<UsersModel> getById(Long id) {
        return userRepository.findById(id);
    }

    public UsersModel updateById(UsersModel request, Long id){
        UsersModel user = userRepository.findById(id).get();

        //seteamos
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setNacDate(request.getNacDate());
        user.setPassword(request.getPassword());
        user.setCreatedDate(request.getCreatedDate());

        //actualizamos y guardamos cambios
        userRepository.save(user);

        return user;
    }

    //boleano por si no se borra por algun motivo
    public Boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
