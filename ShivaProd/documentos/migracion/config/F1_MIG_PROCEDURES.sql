Prompt Procedure HABILITAR_CONSTRAINT;
CREATE OR REPLACE PROCEDURE HABILITAR_CONSTRAINT (pstatus NUMBER)
IS
   ssql   VARCHAR2 (100);
BEGIN
   IF (pstatus = 0)
   THEN
      FOR i IN (SELECT table_name,
                       constraint_name        --disable first the foreign key
                  FROM user_constraints
                 WHERE constraint_type = 'R' AND status = 'ENABLED')
      LOOP
         IF SUBSTR(i.table_name, 1, 8) IN ('SHV_MIG_', 'SHV_COB_', 'SHV_VAL', 'SHV_WF_%', 'SHV_CNT_', 'SHV_BOL_', 'SHV_REL_', 'SHV_TAL_', 'SHV_AVC_')
         THEN
            ssql :=
                  'ALTER TABLE '
               || i.table_name
               || ' DISABLE CONSTRAINT '
               || i.constraint_name;

            EXECUTE IMMEDIATE ssql;
         END IF;
      END LOOP i;

      FOR i IN (SELECT table_name,
                       constraint_name         -- then disable all constraints
                  FROM user_constraints
                 WHERE status = 'ENABLED')
      LOOP
         IF SUBSTR(i.table_name, 1, 8) IN ('SHV_MIG_', 'SHV_COB_', 'SHV_VAL', 'SHV_WF_%', 'SHV_CNT_', 'SHV_BOL_', 'SHV_REL_', 'SHV_TAL_', 'SHV_AVC_')
         THEN
            ssql :=
                  'ALTER TABLE '
               || i.table_name
               || ' DISABLE CONSTRAINT '
               || i.constraint_name;

            EXECUTE IMMEDIATE ssql;
         END IF;
      END LOOP i;
   ELSE
      FOR i IN (SELECT table_name,
                       constraint_name        -- then disable all constraints
                  FROM user_constraints
                 WHERE constraint_type <> 'R' AND status = 'DISABLED')
      LOOP
         IF SUBSTR(i.table_name, 1, 8) IN ('SHV_MIG_', 'SHV_COB_', 'SHV_VAL', 'SHV_WF_%', 'SHV_CNT_', 'SHV_BOL_', 'SHV_REL_', 'SHV_TAL_')
         THEN
            ssql :=
                  'ALTER TABLE '
               || i.table_name
               || ' ENABLE CONSTRAINT '
               || i.constraint_name;

            EXECUTE IMMEDIATE ssql;
         END IF;
      END LOOP i;

      FOR i IN (SELECT table_name,
                       constraint_name         -- then disable all constraints
                  FROM user_constraints
                 WHERE status = 'DISABLED')
      LOOP
         IF SUBSTR(i.table_name, 1, 8) IN ('SHV_MIG_', 'SHV_COB_', 'SHV_VAL', 'SHV_WF_%', 'SHV_CNT_', 'SHV_BOL_', 'SHV_REL_', 'SHV_TAL_', 'SHV_AVC_')
         THEN
            ssql :=
                  'ALTER TABLE '
               || i.table_name
               || ' ENABLE CONSTRAINT '
               || i.constraint_name;

            EXECUTE IMMEDIATE ssql;
         END IF;
      END LOOP i;
   END IF;
END habilitar_constraint;
/

Prompt Procedure TRUNCAR_TABLES_ROLLBACK;
CREATE OR REPLACE PROCEDURE TRUNCAR_TABLES
IS
BEGIN
   FOR i IN (SELECT   table_name
                 FROM user_tables
                WHERE table_name like 'SHV_MIG_%' OR table_name like 'SHV_COB_%' OR table_name like 'SHV_VAL_%' OR table_name like 'SHV_WF_%' OR table_name like 'SHV_CNT_%' or table_name like 'SHV_BOL_' or table_name like 'SHV_REL_' or table_name 'SHV_TAL_' or table_name like 'SHV_AVC_')) 
             ORDER BY table_name)
   LOOP
      EXECUTE IMMEDIATE 'TRUNCATE TABLE ' || i.table_name;
   END LOOP i;
END truncar_tables;
/

Prompt Procedure TRUNCAR_TABLES_MIGRACION;
CREATE OR REPLACE PROCEDURE TRUNCAR_TABLES
IS
BEGIN
   FOR i IN (SELECT   table_name
                 FROM user_tables
                WHERE table_name like 'SHV_MIG_%'
             ORDER BY table_name)
   LOOP
      EXECUTE IMMEDIATE 'TRUNCATE TABLE ' || i.table_name;
   END LOOP i;
END truncar_tables;
/
QUIT