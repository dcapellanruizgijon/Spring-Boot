package com.example.examen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class pc {
    private String id;
    private int precio;
    private String perifericoDeRegalo;
    private String imagePath;

}
