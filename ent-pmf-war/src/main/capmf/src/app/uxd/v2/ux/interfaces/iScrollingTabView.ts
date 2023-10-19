export class IScrollingTabViews {
  tabs: [IScrollingTabView];
}

export class IScrollingTabView {
  heading: String;
  subHeading: String;
  tabId?: String;
  isActive?: boolean;
  content?: String;
}
