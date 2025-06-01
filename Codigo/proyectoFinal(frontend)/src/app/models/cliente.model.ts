/**
 * Interfaz que define la estructura de un cliente en el sistema
 * Representa los datos personales y de contacto de un cliente
 */
export interface Cliente {
    /** ID único del cliente (opcional) */
    id?: number;
    /** Nombre completo del cliente */
    nombre: string;
    /** Correo electrónico del cliente */
    correo_electronico: string;
    /** Número de teléfono del cliente */
    telefono: string;
    /** Dirección física del cliente */
    direccion: string;
    /** Número de identificación (DNI) del cliente */
    dni: string;
    /** Lista de pedidos asociados al cliente (opcional) */
    pedidos?: any[];
} 