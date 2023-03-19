package nrt.common.microservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEmail {

    protected String template;
    protected String emailTo;
    protected String subjetc;
    protected List<String> listOfFiles;
}
