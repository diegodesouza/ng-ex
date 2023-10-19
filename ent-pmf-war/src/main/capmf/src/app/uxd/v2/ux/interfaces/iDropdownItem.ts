export interface IDropdownItem {
  label: string;
  value: any;
  disabled?: boolean;
  selected?: boolean;
  hidden?: boolean;
  filtered?: boolean;
  analytics?: string;
}
