export interface ICalloutBlock {
  class: string;
  item: [
    {
      title: string,
      body: string,
      img?: string,
      icon?: string,
      linkText: string,
      linkUrl: string
    }
  ];
}
