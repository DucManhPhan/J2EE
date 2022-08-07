
CREATE OR REPLACE FUNCTION public.employee_audit_trigger_func()
  RETURNS TRIGGER
  LANGUAGE PLPGSQL
  AS
$$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO employee_audit (
      employee_id,
      old_row_data,
      new_row_data,
      dml_type,
      dml_timestamp
    ) VALUES (
      NEW.id,
      null,
      to_jsonb(NEW),
      'INSERT',
      CURRENT_TIMESTAMP
    );

    PERFORM pg_notify('watch_create_event_table_employee', row_to_json(NEW)::text);
    RETURN NEW;
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO employee_audit (
      employee_id,
      old_row_data,
      new_row_data,
      dml_type,
      dml_timestamp
    ) VALUES (
      NEW.id,
      to_jsonb(OLD),
      to_jsonb(NEW),
      'UPDATE',
      CURRENT_TIMESTAMP
    );

    PERFORM pg_notify('watch_update_event_table_employee', row_to_json(NEW)::text);
    RETURN NEW;
  END IF;
END;
$$