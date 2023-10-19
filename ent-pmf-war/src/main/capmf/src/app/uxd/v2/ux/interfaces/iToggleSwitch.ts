export class IToggleSwitch {
  id: string;
  legendSrOnly: boolean;
  labelText: string;
  options: [Option];
}

export class Option {
  value: string;
  label: string;
}
