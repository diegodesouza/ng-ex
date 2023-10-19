import { ICarouselItem } from './iCarouselItem';

/**
 *  Alphabet Carousel/Ribbon/Tabs component data interface.
 *
 *  @param  String                { idPrefix } ID to assign across Alphabet component; accessibility
 *  @param  Array<ICarouselItem>  { carouselItems } Array of ICarouselItem objects. Refer to ICarouselItem.ts interface (optional)
 *  @see    ICarouselItem
 */
export interface IAlphabetCarousel {
  idPrefix?: string; // Optional; default provided in component
  alphabetRanges: Array<ICarouselItem>;
}
