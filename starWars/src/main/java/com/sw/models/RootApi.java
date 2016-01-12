package com.sw.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode()
@lombok.ToString()
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootApi {

	private String films;
	private String people;
	private String planets;
	private String species;
	private String starships;
	private String vehicles;
	

	
}
