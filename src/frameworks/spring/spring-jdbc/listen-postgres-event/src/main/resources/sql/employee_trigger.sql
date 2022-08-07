
CREATE TRIGGER employee_audit_trigger
  BEFORE INSERT OR UPDATE OR DELETE
  ON public.employee
  FOR EACH ROW
  EXECUTE PROCEDURE public.employee_audit_trigger_func();
