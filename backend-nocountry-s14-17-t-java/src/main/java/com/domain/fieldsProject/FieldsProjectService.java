package com.domain.fieldsProject;

import java.util.List;
import com.dto.FieldsProjectModel;

public interface FieldsProjectService {

    FieldsProjectModel createFieldsProject(FieldsProjectModel fieldsProjectModel);
    FieldsProjectModel updateFieldsProject(Long fieldsProjectId, FieldsProjectModel fieldsProjectModel);

    FieldsProjectModel getFieldsProjectById(Long id);
    List<FieldsProjectModel> getAllFieldsProject();

    void deleteFieldsProject(Long fieldsProjectId);
}
