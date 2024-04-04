package com.domain.fields;

import java.util.List;
import com.dto.FieldsModel;

public interface FieldsService {

    FieldsModel createFields(FieldsModel fieldsModel);
    FieldsModel updateFields(Long fieldsId, FieldsModel fieldsModel);

    FieldsModel getFieldsById(Long id);
    List<FieldsModel> getAllFields();

    void deleteFields(Long fieldsId);
}
