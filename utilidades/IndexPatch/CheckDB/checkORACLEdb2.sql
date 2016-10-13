
CREATE OR REPLACE PROCEDURE deleteDuplicates
IS
	-- Data row
	var_alcance CLOB;
	var_alt_description CLOB;
	var_alt_keyword CLOB;
	var_alt_literal_arbol_c CLOB;
	var_alt_title CLOB;
	var_ambito CLOB;
	var_autor CLOB;
	var_con_sin_secuencia CLOB;
	var_contexto CLOB;
	var_contribucion CLOB;
	var_contribuidor CLOB;
	var_description CLOB;
	var_description_objetivo CLOB;
	var_destinatarios CLOB;
	var_edad CLOB;
	var_fecha_publicacion CLOB;
	var_formato CLOB;
	var_fuente CLOB;
	var_hora_publicacion CLOB;
	var_id NUMBER(38,0);
	var_idioma VARCHAR2(255);
	var_imagen CLOB;
	var_keyword CLOB;
	var_licencias CLOB;
	var_literal_arbol_c CLOB;
	var_localizador CLOB;
	var_nivel_agregacion CLOB;
	var_procesos_cognitivos CLOB;
	var_publicador CLOB;
	var_relacion CLOB;
	var_second_description CLOB;
	var_second_keywords CLOB;
	var_second_title CLOB;
	var_tamanio CLOB;
	var_taxonomia CLOB;
	var_taxonomia_nodo CLOB;
	var_taxonomia_path CLOB;
	var_tipo_recurso CLOB;
	var_title CLOB;
	var_valoracion VARCHAR2(255);

	-- last_id NUMBER(38,0);
	-- new_id NUMBER(38,0);
	duplicate_id VARCHAR2(255);

    CURSOR duplicateIDs IS SELECT IDENTIFICADOR 
							FROM INDICE_BUSQUEDA 
							GROUP BY IDENTIFICADOR 
							HAVING (COUNT(IDENTIFICADOR)>1);
BEGIN 		
	OPEN duplicateIDs; 
	LOOP
		FETCH duplicateIDs INTO duplicate_id;
	
		-- EXIT WHEN duplicateIDs%NOTFOUND;
		IF (duplicateIDs%NOTFOUND) THEN
			EXIT;
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
		AND ROWNUM=1;
		
		-- Elimino todos los repetidos
		DELETE FROM INDICE_BUSQUEDA WHERE IDENTIFICADOR = duplicate_id;
		COMMIT;
		
		-- Obtain a new ID to insert the new element
		-- SELECT MAX(ID) INTO last_id FROM INDICE_BUSQUEDA;
		-- new_id := last_id + 1;
		
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
			ID,
			IDENTIFICADOR,
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
		
	END LOOP;
	CLOSE duplicateIDs;
END; 


EXECUTE deleteDuplicates();
-- CALL deleteDuplicates();


UPDATE INDICE_BUSQUEDA 
SET FORMATO='unknown'
WHERE FORMATO IS NULL;