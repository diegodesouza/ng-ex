export interface IDissmisableAlertEvent {
  eventType: string;
  type: string;
  content: any;
  timeout?: number;
}
