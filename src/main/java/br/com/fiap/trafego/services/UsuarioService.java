package br.com.fiap.trafego.services;

import br.com.fiap.trafego.dto.UsuarioCadastroDTO;
import br.com.fiap.trafego.dto.UsuarioExibicaoDTO;
import br.com.fiap.trafego.entities.Usuario;
import br.com.fiap.trafego.repositories.UsuarioRepository;
import br.com.fiap.trafego.services.exceptions.DatabaseException;
import br.com.fiap.trafego.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO){

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDTO(usuarioSalvo);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            usuarioRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @Transactional
    public UsuarioExibicaoDTO update (Long id, UsuarioExibicaoDTO dto){
        try{
            Usuario entity= usuarioRepository.getReferenceById(id);
            copyDtoToEntity (dto, entity);
            entity= usuarioRepository.save(entity);
            return new UsuarioExibicaoDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    private void copyDtoToEntity(UsuarioExibicaoDTO dto, Usuario entity){
       entity.setNome(dto.nome());
       entity.setEmail(dto.email());


    }
}

