package com.domain.fields;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.FieldsModel;
import com.domain.fields.Fields;
import com.domain.fields.FieldsRepository;
import com.domain.fields.FieldsService;

@Service
public class FieldsServiceImpl implements FieldsService{

    private final ModelMapper modelMapper;
    private final FieldsRepository fieldsRepository;

    public FieldsServiceImpl(
        ModelMapper modelMapper,
        FieldsRepository fieldsRepository
    ) {
        this.modelMapper = modelMapper;
        this.fieldsRepository = fieldsRepository;
    }

    @Transactional
    @Override
    public FieldsModel createFields(FieldsModel fieldsModel) {
        
        Fields fields = modelMapper.map(fieldsModel, Fields.class);
        fields = fieldsRepository.save(fields);
        fieldsModel.setId(fields.getId());
        return modelMapper.map(fields, FieldsModel.class);
    }

    @Transactional
    @Override
    public FieldsModel updateFields(Long id, FieldsModel fieldsModel) {

        if (fieldsRepository.existsById(id)) {
            Fields fieldsActualizado = modelMapper.map(fieldsModel, Fields.class);
            fieldsRepository.save(fieldsActualizado);
            return fieldsModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Fields no encontrado para actualizar."
        );
    }

    @Override
    public FieldsModel getFieldsById(Long id) {

        Fields fields = fieldsRepository.findById(id).orElse(null);
        if (fields == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Fields no encontrado."
            );
        }
        return modelMapper.map(fields, FieldsModel.class);
    }

    @Override
    public List<FieldsModel> getAllFields() {

        List<Fields> entity = fieldsRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, FieldsModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteFields(Long id) {
        if (fieldsRepository.existsById(id)) {
            fieldsRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Fields no encontrada para eliminar."
            );
        }
    }
}
