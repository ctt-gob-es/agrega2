
DROP PROCEDURE IF EXISTS deleteDuplicates;
DELIMITER |
CREATE PROCEDURE deleteDuplicates ()
BEGIN	
	-- Data row
	DECLARE var_alcance MEDIUMTEXT;
	DECLARE var_alt_description MEDIUMTEXT;
	DECLARE var_alt_keyword MEDIUMTEXT;
	DECLARE var_alt_literal_arbol_c MEDIUMTEXT;
	DECLARE var_alt_title MEDIUMTEXT;
	DECLARE var_ambito MEDIUMTEXT;
	DECLARE var_autor MEDIUMTEXT;
	DECLARE var_con_sin_secuencia MEDIUMTEXT;
	DECLARE var_contexto MEDIUMTEXT;
	DECLARE var_contribucion MEDIUMTEXT;
	DECLARE var_contribuidor MEDIUMTEXT;
	DECLARE var_description MEDIUMTEXT;
	DECLARE var_description_objetivo MEDIUMTEXT;
	DECLARE var_destinatarios MEDIUMTEXT;
	DECLARE var_edad MEDIUMTEXT;
	DECLARE var_fecha_publicacion MEDIUMTEXT;
	DECLARE var_formato MEDIUMTEXT;
	DECLARE var_fuente MEDIUMTEXT;
	DECLARE var_hora_publicacion MEDIUMTEXT;
	DECLARE var_id BIGINT;
	DECLARE var_idioma VARCHAR(128);
	DECLARE var_imagen MEDIUMTEXT;
	DECLARE var_keyword MEDIUMTEXT;
	DECLARE var_licencias MEDIUMTEXT;
	DECLARE var_literal_arbol_c MEDIUMTEXT;
	DECLARE var_localizador MEDIUMTEXT;
	DECLARE var_nivel_agregacion MEDIUMTEXT;
	DECLARE var_procesos_cognitivos MEDIUMTEXT;
	DECLARE var_publicador MEDIUMTEXT;
	DECLARE var_relacion MEDIUMTEXT;
	DECLARE var_second_description MEDIUMTEXT;
	DECLARE var_second_keywords MEDIUMTEXT;
	DECLARE var_second_title MEDIUMTEXT;
	DECLARE var_tamanio MEDIUMTEXT;
	DECLARE var_taxonomia MEDIUMTEXT;
	DECLARE var_taxonomia_nodo MEDIUMTEXT;
	DECLARE var_taxonomia_path MEDIUMTEXT;
	DECLARE var_tipo_recurso MEDIUMTEXT;
	DECLARE var_title MEDIUMTEXT;
	DECLARE var_valoracion MEDIUMTEXT;

	DECLARE duplicate_id VARCHAR(128);
    DECLARE done INT DEFAULT 0;

    DECLARE duplicateIDs CURSOR FOR SELECT IDENTIFICADOR 
									FROM INDICE_BUSQUEDA 
									GROUP BY IDENTIFICADOR 
									HAVING (COUNT(IDENTIFICADOR)>1);
									
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	
	OPEN duplicateIDs; 
	duplicateIDs_loop: LOOP

		FETCH duplicateIDs INTO duplicate_id;

		IF done THEN  
			LEAVE duplicateIDs_loop;
		END IF;

		-- Guardo los datos de uno de los repetidos
		SELECT 
			ALCANCE,
			ALT_DESCRIPTION,
			ALT_KEYWORD,
			ALT_LITERAL_ARBOL_C,
			ALT_TITLE,
			AMBITO,
			AUTOR,
			CON_SIN_SECUENCIA,
			CONTEXTO,
			CONTRIBUCION,
			CONTRIBUIDOR,
			DESCRIPTION,
			DESCRIPTION_OBJETIVO,
			DESTINATARIOS,
			EDAD,
			FECHA_PUBLICACION,
			FORMATO,
			FUENTE,
			HORA_PUBLICACION,
			ID,
			IDIOMA,
			IMAGEN,
			KEYWORD,
			LICENCIAS,
			LITERAL_ARBOL_C,
			LOCALIZADOR,
			NIVEL_AGREGACION,
			PROCESOS_COGNITIVOS,
			PUBLICADOR,
			RELACION,
			SECOND_DESCRIPTION,
			SECOND_KEYWORDS,
			SECOND_TITLE,
			TAMANIO,
			TAXONOMIA,
			TAXONOMIA_NODO,
			TAXONOMIA_PATH,
			TIPO_RECURSO,
			TITLE,
			VALORACION
		INTO 
			var_alcance,
			var_alt_description,
			var_alt_keyword,
			var_alt_literal_arbol_c,
			var_alt_title,
			var_ambito,
			var_autor,
			var_con_sin_secuencia,
			var_contexto,
			var_contribucion,
			var_contribuidor,
			var_description,
			var_description_objetivo,
			var_destinatarios,
			var_edad,
			var_fecha_publicacion,
			var_formato,
			var_fuente,
			var_hora_publicacion,
			var_id,
			var_idioma,
			var_imagen,
			var_keyword,
			var_licencias,
			var_literal_arbol_c,
			var_localizador,
			var_nivel_agregacion,
			var_procesos_cognitivos,
			var_publicador,
			var_relacion,
			var_second_description,
			var_second_keywords,
			var_second_title,
			var_tamanio,
			var_taxonomia,
			var_taxonomia_nodo,
			var_taxonomia_path,
			var_tipo_recurso,
			var_title,
			var_valoracion
		FROM INDICE_BUSQUEDA 
		WHERE IDENTIFICADOR = duplicate_id
		LIMIT 1;
		
		-- Elimino todos los repetidos
		DELETE INDICE_BUSQUEDA FROM INDICE_BUSQUEDA WHERE IDENTIFICADOR = duplicate_id;
		COMMIT;
		
		-- Inserto el guardado
		INSERT INTO INDICE_BUSQUEDA (
			ALCANCE,
			ALT_DESCRIPTION,
			ALT_KEYWORD,
			ALT_LITERAL_ARBOL_C,
			ALT_TITLE,
			AMBITO,
			AUTOR,
			CON_SIN_SECUENCIA,
			CONTEXTO,
			CONTRIBUCION,
			CONTRIBUIDOR,
			DESCRIPTION,
			DESCRIPTION_OBJETIVO,
			DESTINATARIOS,
			EDAD,
			FECHA_PUBLICACION,
			FORMATO,
			FUENTE,
			HORA_PUBLICACION,
			IDENTIFICADOR,
			ID,
			IDIOMA,
			IMAGEN,
			KEYWORD,
			LICENCIAS,
			LITERAL_ARBOL_C,
			LOCALIZADOR,
			NIVEL_AGREGACION,
			PROCESOS_COGNITIVOS,
			PUBLICADOR,
			RELACION,
			SECOND_DESCRIPTION,
			SECOND_KEYWORDS,
			SECOND_TITLE,
			TAMANIO,
			TAXONOMIA,
			TAXONOMIA_NODO,
			TAXONOMIA_PATH,
			TIPO_RECURSO,
			TITLE,
			VALORACION)
		VALUES (
			var_alcance,
			var_alt_description,
			var_alt_keyword,
			var_alt_literal_arbol_c,
			var_alt_title,
			var_ambito,
			var_autor,
			var_con_sin_secuencia,
			var_contexto,
			var_contribucion,
			var_contribuidor,
			var_description,
			var_description_objetivo,
			var_destinatarios,
			var_edad,
			var_fecha_publicacion,
			var_formato,
			var_fuente,
			var_hora_publicacion,
			var_id,
			duplicate_id,
			var_idioma,
			var_imagen,
			var_keyword,
			var_licencias,
			var_literal_arbol_c,
			var_localizador,
			var_nivel_agregacion,
			var_procesos_cognitivos,
			var_publicador,
			var_relacion,
			var_second_description,
			var_second_keywords,
			var_second_title,
			var_tamanio,
			var_taxonomia,
			var_taxonomia_nodo,
			var_taxonomia_path,
			var_tipo_recurso,
			var_title,
			var_valoracion);
		
	END LOOP duplicateIDs_loop;
	CLOSE duplicateIDs;

END; | 
DELIMITER ;


CALL deleteDuplicates();


UPDATE INDICE_BUSQUEDA 
SET FORMATO="unknown"
WHERE FORMATO="";


