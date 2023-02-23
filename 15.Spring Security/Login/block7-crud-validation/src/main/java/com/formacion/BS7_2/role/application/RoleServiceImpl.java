package com.formacion.BS7_2.role.application;

import com.formacion.BS7_2.exception.EntityNotFoundException;
import com.formacion.BS7_2.exception.UnprocessableEntityException;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;
import com.formacion.BS7_2.role.domain.Role;
import com.formacion.BS7_2.role.infraestructure.dto.RoleInputDto;
import com.formacion.BS7_2.role.infraestructure.dto.RoleOutputDto;
import com.formacion.BS7_2.role.infraestructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepo;
    @Autowired
    private  PersonDaoRepository personRepo;

    @Override
    public String addRoles(RoleInputDto roleInputDto) throws Exception{
        Role role = new Role(roleInputDto);
        roleRepo.save(role);
        return "Role saved";
    }

    @Override
    public String addRolePerson(String username, String roleName) {
     Person person =   personRepo.findByUsername(username);
       if(username == null){
           throw  new EntityNotFoundException("username does not exist",404,new Date());
       }
        Role role =  roleRepo.findByName(roleName);
       if(role == null){
           throw new EntityNotFoundException("does not exist",404,new Date());
       }

        Collection<Role> roles = person.getRoles();
        if(roles.isEmpty()){
            person.getRoles().add(role);
            personRepo.save(person);
        }
        else if(person.getRoles().contains(role)){
            throw new UnprocessableEntityException("Role ya se encuentra asignado",422,new Date());
        }
        person.getRoles().add(role);
        personRepo.save(person);
        return "Role added";
    }

    @Override
    public String deleteRolePerson(String username, Long id) throws Exception {
        Person person = personRepo.findByUsername(username);
        if(username == null){
            throw  new EntityNotFoundException("username does not exist",404,new Date());
        }
       Role role = roleRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Does not exist",404,new Date()));
        if(person.getRoles().size()>0){
            person.getRoles().remove(role);
        }
        else{
            throw new UnprocessableEntityException("can not deleted role",422,new Date());
        }
        return "role deleted";
    }



    @Override
    public List<RoleOutputDto> ShowAllRoles() {
        List<RoleOutputDto> roleOutputDtoList = new ArrayList<>();
        roleRepo.findAll().forEach(role -> {
            RoleOutputDto roleOutputDto = new RoleOutputDto(role);
            roleOutputDtoList.add(roleOutputDto);
        });
        return roleOutputDtoList;
    }

    @Override
    public String deleteRoles(Long id) {
        Optional<Role> roleOptional = roleRepo.findById(id);
        if(roleOptional.isEmpty()){
            throw new UnprocessableEntityException("does not exist",404,new Date());
        }
        roleRepo.delete(roleOptional.get());
        return "role deleted";

    }

    @Override
    public RoleOutputDto updateRole(RoleInputDto roleInputDto, Long id) {
        Role role;
        Optional<Role> roleOptional = roleRepo.findById(id);
        if(roleOptional.isEmpty()){
            throw new EntityNotFoundException("roles does not exist",404,new Date());
        }
        role = roleOptional.get();
        role.setName(roleInputDto.getName());
        return new RoleOutputDto(role);
    }

    @Override
    public RoleOutputDto findByRoleName(String roleName) throws Exception {
        Role role= roleRepo.findByName(roleName);
        if(role == null){
            throw new EntityNotFoundException("Person does not exist",404, new Date());
        }
        return new RoleOutputDto(role);
    }

    public  Person addRoleAdmin(Person person){
        Role role = new Role();
        role.setName("ADMIN");
        person.getRoles().add(role);
        personRepo.save(person);
        return person;
    }
    public  Person addRoleAUSER(Person person){
        Role role = new Role();
        role.setName("USER");
        person.getRoles().add(role);
        personRepo.save(person);
        return person;
    }


}
