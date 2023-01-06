package com.distribuida.servicios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;

public interface ServicioHolaMundo {
    String hola();
}
